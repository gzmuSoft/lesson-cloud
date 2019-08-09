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
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Exam Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-28 17:24:38
 *
 * @author ljq
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping(LessonResource.EXAM_SEARCH)
public class ExamController extends BaseController<Exam, ExamService, Long> {

    private final @NonNull ExamService examService;

    /**
     * 根据班级列表和课程信息获取所有分页过后的考试信息
     *
     * @param courseId courseId
     * @return result
     */
    @GetMapping("/classAndCourse")
    public HttpEntity<?> classAndCourse(String courseId, String classIds,
                                        @PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return ResponseEntity.ok(examService.searchByClassAndCourse(courseId, classIds, pageable));
    }

    /**
     * 获取当前登录学生的考试信息
     * 只有拥有学生角色以后才能够访问当前接口
     *
     * @param pageable 分页
     * @return 响应
     */
    @GetMapping("/student")
    @Secured("ROLE_STUDENT")
    public HttpEntity<?> fromStudent(@PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        // 获取当前登录用户
        Student student = OauthHelper.student();
        return ResponseEntity.ok(
                examService.searchByStudentPage(student, pageable)
        );
    }
}
