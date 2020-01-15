package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.constant.LessonResource;
import cn.edu.gzmu.model.entity.Judgement;
import cn.edu.gzmu.service.JudgementService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Judgement Controller
 *
 * @author yxf
 * @version 1.0
 * @date 2019-5-28 17:48:37
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping(LessonResource.JUDGEMENT_SEARCH)
public class JudgementController extends BaseController<Judgement, JudgementService, Long> {

    private final @NonNull JudgementService judgementService;

    /**
     * 查询公开的判断题
     *
     * @return org.springframework.http.HttpEntity<?>
     * @author Soul
     * @date 2020/1/10 17:21
     */
    @GetMapping("/findAllByIsPublic")
    public HttpEntity<?> findAllByIsPublic(@PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return ResponseEntity.ok(judgementService.findAllByIsPublic(true, pageable));
    }

    /**
     * 根据课程 Id 分页查询公开的判断题
     *
     * @param courseId 课程Id
     * @return org.springframework.http.HttpEntity<?>
     * @author Soul
     * @date 2020/1/10 17:21
     */
    @GetMapping("/findAllByCourseIdAndIsPublic")
    public HttpEntity<?> findAllByCourseIdAndIsPublic(Long courseId, @PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return ResponseEntity.ok(judgementService.findAllByCourseIdAndIsPublic(courseId, true, pageable));
    }

    /**
     * 根据课程 Id 和章节 Id 分页查询公开的判断题
     *
     * @param courseId  课程Id
     * @param sectionId 章节Id
     * @return org.springframework.http.HttpEntity<?>
     * @author Soul
     * @date 2020/1/10 17:21
     */
    @GetMapping("/findAllByCourseIdAndSectionIdAndIsPublic")
    public HttpEntity<?> findAllByCourseIdAndSectionIdAndIsPublic(Long courseId, Long sectionId, @PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return ResponseEntity.ok(judgementService.findAllByCourseIdAndSectionIdAndIsPublic(courseId, sectionId, true, pageable));
    }

    /**
     * 根据课程 Id 和章节 Id 和知识点 Id 分页查询公开的判断题
     *
     * @param courseId    课程Id
     * @param sectionId   章节Id
     * @param knowledgeId 知识点Id
     * @return org.springframework.http.HttpEntity<?>
     * @author Soul
     * @date 2020/1/10 17:21
     */
    @GetMapping("/findAllByCourseIdAndSectionIdAndKnowledgeIdAndIsPublic")
    public HttpEntity<?> findAllByCourseIdAndSectionIdAndKnowledgeIdAndIsPublic(Long courseId, Long sectionId, Long knowledgeId, @PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return ResponseEntity.ok(judgementService.findAllByCourseIdAndSectionIdAndKnowledgeIdAndIsPublic(courseId, sectionId, knowledgeId, true, pageable));
    }

    /**
     * 根据题目部分内容模糊分页查询公开的判断题
     *
     * @param name 内容
     * @return org.springframework.http.HttpEntity<?>
     * @author Soul
     * @date 2020/1/10 17:21
     */
    @GetMapping("/findByNameContainingAndIsPublic")
    public HttpEntity<?> findByNameContainingAndIsPublic(String name, @PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return ResponseEntity.ok(judgementService.findByNameContainingAndIsPublic(name, true, pageable));
    }
}
