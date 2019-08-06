package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.constant.LessonResource;
import cn.edu.gzmu.model.entity.Section;
import cn.edu.gzmu.service.SectionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Section Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-28 17:24:38
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping(LessonResource.SECTION)
public class SectionController extends BaseController<Section, SectionService, Long> {
    private final @NonNull SectionService sectionService;
    @GetMapping("/course/{id}")
    public HttpEntity<?> sectionById(@PathVariable Long id) {
        return ResponseEntity.ok(sectionService.searchByCourseId(id));
    }


}
