package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.constant.LessonResource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
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
@RequestMapping(LessonResource.STUDENT_SEARCH)
public class StudentController {

}
