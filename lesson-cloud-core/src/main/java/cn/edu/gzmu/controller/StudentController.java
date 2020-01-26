package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.constant.LessonResource;
import cn.edu.gzmu.model.dto.PaperInfo;
import cn.edu.gzmu.service.ExamBusinessService;
import cn.edu.gzmu.service.StudentService;
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

/**
 * Student Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-24 14:18:22
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping(LessonResource.STUDENT_SEARCH)
public class StudentController {
    private final @NonNull StudentService studentService;

    private final @NonNull ExamBusinessService examBusinessService;


    @GetMapping("/semester/{id}/logicClass")
    public HttpEntity<?> findLogicClassBySemesterId(
            @PathVariable("id") Long semesterId,
            @PageableDefault(sort = {"sort", "id"}) Pageable pageable
    ) {
        return ResponseEntity.ok(studentService.findLogicClassBySemesterId(semesterId, pageable));
    }

    @GetMapping("/course/{id}/exam")
    public HttpEntity<?> findExamByCourseId(
            @PathVariable("id") Long semesterId,
            @PageableDefault(sort = {"sort", "id"}) Pageable pageable
    ) {
        return ResponseEntity.ok(studentService.findExamByCourseId(semesterId, pageable));
    }

    /**
     * 开始一场考试
     *
     * @param examId examId
     * @return 结果
     */
    @GetMapping("/exam/{id}")
    public HttpEntity<?> startExam(@PathVariable("id") Long examId) {
        return ResponseEntity.ok(examBusinessService.startExam(examId));
    }

    @GetMapping("exam/{id}/recovery")
    public HttpEntity<?> recoveryExam(@PathVariable("id") Long examId) {
        return ResponseEntity.ok(examBusinessService.recoveryExam(examId));
    }

    /**
     * 结束一场考试
     *
     * @param examId 考试id
     * @return 结果
     */
    @PostMapping("/exam/{id}")
    public HttpEntity<?> stopExam(@PathVariable("id") Long examId, @RequestBody PaperInfo paperInfo) {
        //TODO 结束一场考试
        paperInfo.setExamId(examId);
        examBusinessService.stopExam(paperInfo);
        return ResponseEntity.ok().build();
    }
}
