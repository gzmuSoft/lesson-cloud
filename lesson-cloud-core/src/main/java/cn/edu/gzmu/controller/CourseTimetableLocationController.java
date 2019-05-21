package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.CourseTimetableLocation;
import cn.edu.gzmu.service.CourseTimetableLocationService;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * CourseTimetableLocation Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-20 9:18:57
 */
@RepositoryRestController
@RequestMapping("/courseTimetableLocations/search")
public class CourseTimetableLocationController extends BaseController<CourseTimetableLocation, CourseTimetableLocationService, Long> {

}
