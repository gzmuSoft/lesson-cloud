package cn.edu.gzmu.controller;

import cn.edu.gzmu.service.helper.OauthHelper;
import cn.edu.gzmu.model.constant.LessonResource;
import cn.edu.gzmu.model.entity.Course;
import cn.edu.gzmu.service.CourseService;
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
 * Course Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-28 17:24:38
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping(LessonResource.COURSE_SEARCH)
public class CourseController extends BaseController<Course, CourseService, Long> {

    private @NonNull CourseService courseService;
    private final @NonNull OauthHelper oauthHelper;

    /**
     * 获取当前登录学生的所有课程信息 - 分页
     * 只有当用户拥有学生角色的时候才能够访问
     *
     * @param pageable 分页信息
     * @return 结果
     */
    @GetMapping(LessonResource.STUDENT)
    public HttpEntity<?> coursesPageFromStuDent(@PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return ResponseEntity.ok(courseService.searchByStudent(pageable));
    }

    /**
     * 获取当前登录教师的所有课程信息
     * 只有当用户拥有教师角色的时候才能够访问
     *
     * @return response
     */
    @GetMapping(LessonResource.TEACHER)
    public HttpEntity<?> coursesPageFromTeacher(@PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return ResponseEntity.ok(courseService.searchByTeacher(oauthHelper.teacher(), pageable));
    }

    /**
     * 根据名称、类型模糊查询。
     * 如果为 self 就差只差自己的
     * 否则就是查所有的
     *
     * @param name     名称，模糊查询
     * @param type     类型，模糊查询
     * @param self     是否是自己的
     * @param pageable 分页
     * @return 结果
     */
    @GetMapping("/byNameAndTypeAndSelf")
    public HttpEntity<?> courseByNameContainingAndType(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String type,
            @RequestParam(defaultValue = "false") Boolean self,
            @PageableDefault(sort = {"sort", "id"}) Pageable pageable
    ) {
        return ResponseEntity.ok(courseService.searchByNameAndTypeAndSelf(name, type, self, pageable));
    }
}
