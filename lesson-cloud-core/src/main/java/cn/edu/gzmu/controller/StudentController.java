package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.constant.LessonResource;
import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.service.StudentService;
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
 * Student Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-24 14:18:22
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping(LessonResource.STUDENT)
public class StudentController extends BaseController<Student, StudentService, Long> {
    private final static String RESOURCE = "/student/search/";
    private final @NonNull StudentService studentService;

    /**
     * 接受前端请求，路径为 /student/search/complete
     *
     * @param pageable 接收分页参数
     * @return http 实体
     */
    @GetMapping(COMPLETE)
    public HttpEntity<?> resources(@PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return ResponseEntity.ok(pagedResources(RESOURCE, studentService.searchAll(pageable)));
    }

}
