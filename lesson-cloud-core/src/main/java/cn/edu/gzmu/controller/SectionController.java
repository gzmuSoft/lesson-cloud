package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.Section;
import cn.edu.gzmu.service.SectionService;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* Section Controller
*
* @author echo
* @version 1.0
* @date 2019-5-7 11:05:31
*/
@RepositoryRestController
@RequestMapping("/sections")
public class SectionController extends BaseController<Section, SectionService, Long> {

}
