package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.constant.LessonResource;
import cn.edu.gzmu.model.constant.QuestionType;
import cn.edu.gzmu.model.dto.ExamInfo;
import cn.edu.gzmu.service.ExamBusinessService;
import cn.edu.gzmu.service.TeacherService;
import cn.edu.gzmu.service.helper.OauthHelper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Teacher Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-28 17:24:38
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping(LessonResource.TEACHER_SEARCH)
public class TeacherController {

    private final @NonNull OauthHelper oauthHelper;
    private final @NonNull TeacherService teacherService;

    private final @NonNull ExamBusinessService examBusinessService;

    @GetMapping("/test")
    public HttpEntity<?> teacherTest() {
        return ResponseEntity.ok(oauthHelper.teacher());
    }

    /**
     * 通过不同条件获取题库.
     *
     * @param pageable 分页
     * @return org.springframework.http.HttpEntity<?>
     * @author Soul
     * @date 2020/1/13 22:04
     * <p>
     * 添加课程 ID 条件.
     * @author Japoul
     * @date 2020/1/23 12:43
     */
    @GetMapping("/question")
    public HttpEntity<?> findQuestionBankByCondition(
            @RequestParam Long courseId,
            @RequestParam(defaultValue = "0") Long passageId,
            @RequestParam(defaultValue = "0") Long sectionId,
            @RequestParam(defaultValue = "0") Long knowledgeId,
            @RequestParam(defaultValue = "") String name,
            @RequestParam(required = false) QuestionType type,
            @RequestParam(defaultValue = "true") boolean isPublic,
            @PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return ResponseEntity.ok(teacherService.findQuestionBankCondition(courseId, passageId, sectionId, knowledgeId, name, type, isPublic, pageable));
    }

    /**
     * 测试考试生成试卷情况
     *
     * @param examId 考试id
     * @return httpEntity
     */
    @GetMapping("/exam/{id}/test")
    public HttpEntity<?> generate(@PathVariable("id") Long examId) {
        return ResponseEntity.ok(examBusinessService.generatePaper(examId));
    }

    @PostMapping("/exam")
    public HttpEntity<?> createExam(@RequestBody ExamInfo examInfo) {
        examBusinessService.createExam(examInfo);
        return ResponseEntity.ok().build();
    }
}
