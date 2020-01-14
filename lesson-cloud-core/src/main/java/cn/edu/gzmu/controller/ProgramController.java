package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.constant.LessonResource;
import cn.edu.gzmu.model.entity.Program;
import cn.edu.gzmu.service.ProgramService;
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
 * Program Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-28 17:24:38
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping(LessonResource.PROGRAM_SEARCH)
public class ProgramController extends BaseController<Program, ProgramService, Long> {

    private final @NonNull ProgramService programService;

    /**
     * 根据 isPublic 分页查询多编程题
     *
     * @param isPublic 是否公开
     * @return org.springframework.http.HttpEntity<?>
     * @author Soul
     * @date 2020/1/10 17:21
     */
    @GetMapping("/findAllByIsPublic")
    public HttpEntity<?> findAllByIsPublic(boolean isPublic, @PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return ResponseEntity.ok(programService.findAllByIsPublic(isPublic, pageable));
    }

    /**
     * 根据课程 id 分页查询编程题
     *
     * @param courseId 课程Id
     * @return .
     */
    @GetMapping("/findAllByCourseIdAndIsPublic")
    public HttpEntity<?> findAllByCourseIdAndIsPublic(Long courseId, boolean isPublic, @PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return ResponseEntity.ok(programService.findAllByCourseIdAndIsPublic(courseId, isPublic, pageable));
    }

    /**
     * 根据课程 Id和章节 Id 分页查询编程题
     *
     * @param courseId  课程Id
     * @param sectionId 章节Id
     * @return org.springframework.http.HttpEntity<?>
     * @author Soul
     * @date 2020/1/10 17:21
     */
    @GetMapping("/findAllByCourseIdAndSectionIdAndIsPublic")
    public HttpEntity<?> findAllByCourseIdAndSectionIdAndIsPublic(Long courseId, Long sectionId, boolean isPublic, @PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return ResponseEntity.ok(programService.findAllByCourseIdAndSectionIdAndIsPublic(courseId, sectionId, isPublic, pageable));
    }

    /**
     * 根据课程 Id和章节 Id 和知识点 id 分页查询编程题
     *
     * @param courseId    课程Id
     * @param sectionId   章节Id
     * @param knowledgeId 知识点Id
     * @return org.springframework.http.HttpEntity<?>
     * @author Soul
     * @date 2020/1/10 17:21
     */
    @GetMapping("/findAllByCourseIdAndSectionIdAndKnowledgeIdAndIsPublic")
    public HttpEntity<?> findAllByCourseIdAndSectionIdAndKnowledgeIdAndIsPublic(Long courseId, Long sectionId, Long knowledgeId, boolean isPublic, @PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return ResponseEntity.ok(programService.findAllByCourseIdAndSectionIdAndKnowledgeIdAndIsPublic(courseId, sectionId, knowledgeId, isPublic, pageable));
    }

    /**
     * 根据题目部分内容模糊分页查询编程题
     *
     * @param name 内容
     * @return org.springframework.http.HttpEntity<?>
     * @author Soul
     * @date 2020/1/10 17:21
     */
    @GetMapping("/findByNameContainingAndIsPublic")
    public HttpEntity<?> findByNameContainingAndIsPublic(String name, boolean isPublic, @PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return ResponseEntity.ok(programService.findByNameContainingAndIsPublic(name, isPublic, pageable));
    }
}
