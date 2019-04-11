package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.Course;
import cn.edu.gzmu.repository.entity.CourseRepository;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* Course Controller
*
* @author echo
* @version 1.0
* @date 19-3-25 14:51
*/
@RepositoryRestController
@RequestMapping("/courses")
public class CourseController extends BaseController<Course, CourseRepository, Long> {

}
