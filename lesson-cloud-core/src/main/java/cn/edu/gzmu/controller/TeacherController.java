package cn.edu.gzmu.controller;

import cn.edu.gzmu.service.helper.OauthHelper;
import cn.edu.gzmu.model.constant.LessonResource;
import cn.edu.gzmu.service.TeacherService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/test")
    public HttpEntity<?> teacherTest() {
        return ResponseEntity.ok(oauthHelper.teacher());
    }

    /**
     * 通过不同条件获取题库
     *
     * @param pageable 分页
     * @return org.springframework.http.HttpEntity<?>
     * @author Soul
     * @date 2020/1/13 22:04
     */
    @GetMapping("/question")
    public HttpEntity<?> findQuestionBankByCondition(
            @RequestParam(defaultValue = "0") Long passageId,
            @RequestParam(defaultValue = "0") Long sectionId,
            @RequestParam(defaultValue = "0") Long knowledgeId,
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "true") boolean isPublic,
            @PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return ResponseEntity.ok(teacherService.findQuestionBankCondition(passageId, sectionId, knowledgeId, name, isPublic, pageable));
    }
}
