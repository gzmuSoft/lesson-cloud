package cn.edu.gzmu.controller;

import cn.edu.gzmu.auth.helper.OauthHelper;
import cn.edu.gzmu.model.constant.LessonResource;
import cn.edu.gzmu.model.entity.Course;
import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.model.entity.Teacher;
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
@RequestMapping(LessonResource.COURSE_SEARCH)
public class CourseController extends BaseController<Course, CourseService, Long> {

    private @NonNull CourseService courseService;

    /**
     * 获取当前登录学生的所有课程信息
     * 只有当用户拥有学生角色的时候才能够访问
     *
     * @return response
     */
    @GetMapping("/student")
    @Secured("ROLE_STUDENT")
    public HttpEntity<?> coursesFromStudent() {
        // 当前登录的学生
        Student student = OauthHelper.student();
        return ResponseEntity.ok(courseService.searchByStudent(student));
    }

    /**
     * 获取当前登录教师的所有课程信息
     * 只有当用户拥有教师角色的时候才能够访问
     *
     * @return response
     */
    @GetMapping("/teacher")
    @Secured("ROLE_TEACHER")
    public HttpEntity<?> coursesFromTeacher() {
        // 当前登录的教师
        Teacher teacher = OauthHelper.teacher();
        return ResponseEntity.ok(courseService.searchByTeacher(teacher));
    }

}
