package cn.edu.gzmu.controller;

import cn.edu.gzmu.auth.helper.OauthHelper;
import cn.edu.gzmu.model.constant.LessonResource;
import cn.edu.gzmu.model.entity.Exam;
import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.service.ExamService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Exam Controller
 *
 * @author echo
 * @author ljq
 *
 * <p>
 * @author hzl
 * @version 1.0
 * @date 2019-5-28 17:24:38
 * @date 2019-8-13 23:48:10
 * 获取到当前教师未发布的考试信息
 * </p>
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping(LessonResource.EXAM_SEARCH)
public class ExamController extends BaseController<Exam, ExamService, Long> {

    private final @NonNull ExamService examService;
    private final @NonNull OauthHelper oauthHelper;

    /**
     * 根据班级列表和课程信息获取所有分页过后的考试信息
     *
     * @param courseId courseId
     * @return result
     */
    @GetMapping("/classAndCourse")
    public HttpEntity<?> classAndCourse(String courseId, String classIds,
                                        @PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return ResponseEntity.ok(examService.searchByLogicClassAndCourse(courseId, classIds, pageable));
    }

    /**
     * 获取当前登录学生的考试信息
     * 只有拥有学生角色以后才能够访问当前接口
     *
     * @param pageable 分页
     * @return 响应
     */
    @GetMapping("/student")
    public HttpEntity<?> fromStudent(@PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        // 获取当前登录用户
        Student student = oauthHelper.student();
        return ResponseEntity.ok(
                examService.searchByStudentPage(student, pageable)
        );
    }

    /**
     * 获取到当前教师未发布的考试信息
     *
     * @param logicClassIds 逻辑班级ids
     * @param courseId      课程id
     * @param pageable      pageable
     * @return 。。。
     */
    @GetMapping("/teacher/unpublish")
    public HttpEntity<?> searchUnPublishExam(
            @RequestParam(defaultValue = "", required = false) String logicClassIds,
            @RequestParam(required = false) String courseId,
            @PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return searchByPublishStatus(logicClassIds, courseId, pageable, false);
    }

    /**
     * 获取到当前教师已发布的考试信息
     *
     * @param logicClassIds 逻辑班级ids
     * @param courseId      课程id
     * @param pageable      pageable
     * @return 。。。
     */
    @GetMapping("/teacher/publish")
    public HttpEntity<?> searchPublishExam(
            @RequestParam(defaultValue = "", required = false) String logicClassIds,
            @RequestParam(required = false) String courseId,
            @PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return searchByPublishStatus(logicClassIds, courseId, pageable, true);
    }

    /**
     * 获取当前登录的学生的指定id所有考试详细信息
     */
    @GetMapping("/details/student/id/{id}")
    public HttpEntity<?> examDetailsById(@PathVariable Long id) {
        Student student = oauthHelper.student();
        return ResponseEntity.ok(examService.searchDetailsById(student, id));
    }

    /**
     * 获取学生的 未完成 的考试信息，需分页
     */
    @GetMapping("/details/student/unfinished")
    public HttpEntity<?> examDetailsByUnFinished(
            @RequestParam(required = false) Boolean type,
            @PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        // 获取当前登录用户
        Student student = oauthHelper.student();
        //finishFlag 未完成
        return ResponseEntity.ok(
                examService.searchDetailsByStudentUnPage(student, pageable, type, 2)
        );
    }

    /**
     * 获取学生的 完成 的考试信息，需分页
     */
    @GetMapping("/details/student/finished")
    public HttpEntity<?> examDetailsByFinished(
            @RequestParam(required = false) Boolean type,
            @PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        // 获取当前登录用户
        Student student = oauthHelper.student();
        //finishFlag 完成
        return ResponseEntity.ok(
                examService.searchDetailsByStudentUnPage(student, pageable, type, 1)
        );
    }

    /**
     * 获取学生的 所有的考试信息，需分页
     */
    @GetMapping("/details/student")
    public HttpEntity<?> examDetailsByAll(
            @RequestParam(required = false) Boolean type,
            @PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        // 获取当前登录用户
        Student student = oauthHelper.student();
        //finishFlag 全都要!
        return ResponseEntity.ok(
                examService.searchDetailsByStudentUnPage(student, pageable, type, 0)
        );
    }

//    /**
//     * 获取到当前教师已发布的考试信息
//     * 1. 会接收零个或多个逻辑班级id，注意：如果传递了逻辑班级 id ，还需要通过一个或者多个逻辑班级 id 来查询
//     * 2. 会接收零个或者单个课程id，注意事项同上
//     * 3. 需分页
//     */
//    @GetMapping("/teacher/publish")
//    @Secured("ROLE_TEACHER")
//    public HttpEntity<?> examFromPublish(String courseIds, String classIds,
//                                         @PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
//
//        return ResponseEntity.ok(examService.examFromPublish(courseIds, classIds, pageable));
//    }


    /**
     * 查询所有考试的详细统计信息，需分页
     * 1. 获取到考试的题目数量（通过组卷规则）
     * 2. 获取到参与考试的所有逻辑班级的名称
     * 3. 获取到考试的应该参与的所有人数（通过当前考试关联的逻辑班级的所有班级人数以及重修人数相加）
     * 4. 获取考试的所有信息
     * 5. 条件可能为逻辑班级id和学期id（可能没有）
     */
    @GetMapping("/details/complete")
    public HttpEntity<?> examDetailsAll(
            @RequestParam(required = false, defaultValue = "") String semesterId,
            @PageableDefault(sort = {"sort", "id"}) Pageable pageable
    ) {
        return ResponseEntity.ok(examService.searchDetailsAll(semesterId, pageable));
    }


    /**
     * 获取到当前教师未发布的考试信息
     *
     * @param logicClassIds 逻辑班级ids
     * @param courseId      课程id
     * @param pageable      pageable
     * @param publish       是否发布
     * @return 。。。
     */
    private HttpEntity<?> searchByPublishStatus(String logicClassIds, String courseId, Pageable pageable, boolean publish) {
        return ResponseEntity.ok(examService.searchExamFromPublishStatus(
                oauthHelper.teacher(), logicClassIds, courseId, pageable, publish));
    }

}
