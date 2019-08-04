package cn.edu.gzmu.controller;

import cn.edu.gzmu.auth.helper.OauthHelper;
import cn.edu.gzmu.model.constant.LessonResource;
import cn.edu.gzmu.model.entity.Course;
import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.service.CourseService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Course Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-28 17:24:38
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping(LessonResource.COURSE)
public class CourseController extends BaseController<Course, CourseService, Long> {

    private @NonNull CourseService courseService;

    /**
     * 获取当前登录学生的所有课程信息
     *
     * @return response
     */
    @GetMapping("/student")
    @Secured("ROLE_STUDENT")
    public HttpEntity<?> courses() {
        Student student = OauthHelper.student();
        return ResponseEntity.ok(courseService.searchByStudent(student));
    }

}
