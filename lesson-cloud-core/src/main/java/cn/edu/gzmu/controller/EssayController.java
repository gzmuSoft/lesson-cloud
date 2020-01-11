package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.constant.LessonResource;
import cn.edu.gzmu.model.entity.Essay;
import cn.edu.gzmu.service.EssayService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Essay Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-28 17:24:38
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping(LessonResource.ESSAY_SEARCH)
public class EssayController extends BaseController<Essay, EssayService, Long> {

    private final @NonNull EssayService essayService;

    /**
     * 根据课程 id 分页查询问答题
     *
     * @param courseId 课程Id
     * @return .
     */
    @GetMapping("/findAllByCourseId")
    public HttpEntity<?> findAllByCourseId(Long courseId, @PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return ResponseEntity.ok(essayService.findAllByCourseId(courseId, pageable));
    }

    /**
     * 根据课程 Id和章节 Id 分页查询问答题
     *
     * @param courseId  课程Id
     * @param sectionId 章节Id
     * @return org.springframework.http.HttpEntity<?>
     * @author Soul
     * @date 2020/1/10 17:21
     */
    @GetMapping("/findAllByCourseIdAndSectionId")
    public HttpEntity<?> findAllBySectionId(Long courseId, Long sectionId, @PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return ResponseEntity.ok(essayService.findAllByCourseIdAndSectionId(courseId, sectionId, pageable));
    }

    /**
     * 根据课程 Id和章节 Id 和知识点 id 分页查询问答题
     *
     * @param courseId    课程Id
     * @param sectionId   章节Id
     * @param knowledgeId 知识点Id
     * @return org.springframework.http.HttpEntity<?>
     * @author Soul
     * @date 2020/1/10 17:21
     */
    @GetMapping("/findAllByCourseIdAndSectionIdAndKnowledgeId")
    public HttpEntity<?> findAllByCourseIdAndSectionIdAndKnowledgeId(Long courseId, Long sectionId, Long knowledgeId, @PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return ResponseEntity.ok(essayService.findAllByCourseIdAndSectionIdAndKnowledgeId(courseId, sectionId, knowledgeId, pageable));
    }

    /**
     * 根据题目部分内容模糊分页查询问答题
     *
     * @param name 内容
     * @return org.springframework.http.HttpEntity<?>
     * @author Soul
     * @date 2020/1/10 17:21
     */
    @GetMapping("/findByNameContaining")
    public HttpEntity<?> findByNameContaining(String name, @PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return ResponseEntity.ok(essayService.findByNameContaining(name, pageable));
    }
}
