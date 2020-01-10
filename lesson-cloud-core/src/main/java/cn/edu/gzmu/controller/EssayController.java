package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.constant.LessonResource;
import cn.edu.gzmu.model.entity.Essay;
import cn.edu.gzmu.service.EssayService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     * 根据课程 ID 查询分页的问答题
     *
     * @param courseId 课程ID
     * @return .
     */
    @GetMapping("/findPageByCourseId")
    public HttpEntity<?> findAllByCourseId(Long courseId, @PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return ResponseEntity.ok(essayService.findAllByCourseId(courseId, pageable));
    }

    /**
     * 根据课程 ID 查询问答题.
     *
     * @param courseId 课程ID
     * @return .
     */
    @GetMapping("/findByCourseId/{courseId}")
    public HttpEntity<?> findAllByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(essayService.findAllByCourseId(courseId));
    }
}
