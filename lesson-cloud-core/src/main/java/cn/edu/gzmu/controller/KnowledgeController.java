package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.constant.LessonResource;
import cn.edu.gzmu.model.entity.Knowledge;
import cn.edu.gzmu.service.KnowledgeService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Knowledge Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-28 17:24:38
 *
 * @author ypx
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping(LessonResource.KNOWLEDGE_SEARCH)
public class KnowledgeController extends BaseController<Knowledge, KnowledgeService, Long> {
    private final @NonNull KnowledgeService knowledgeService;

    @GetMapping("/sectionAndCourse")
    public HttpEntity<?> searchBySectionIdAndCourseId(Long sectionId, Long courseId) {
        return ResponseEntity.ok(knowledgeService.searchBySectionIdAndCourseId(sectionId,courseId));
    }
}
