package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.constant.LessonResource;
import cn.edu.gzmu.model.entity.CourseTimetableLocation;
import cn.edu.gzmu.service.CourseTimetableLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * CourseTimetableLocation Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-28 17:24:38
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping(LessonResource.COURSE_TIMETABLE_LOCATION_SEARCH)
public class CourseTimetableLocationController extends BaseController<CourseTimetableLocation, CourseTimetableLocationService, Long> {

}
