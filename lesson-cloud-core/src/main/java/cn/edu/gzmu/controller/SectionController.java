package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.Section;
import cn.edu.gzmu.repository.entity.SectionRepository;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* Section Controller
*
* @author echo
* @version 1.0
* @date 19-3-25 14:51
*/
@RepositoryRestController
@RequestMapping("/sections")
public class SectionController extends BaseController<Section, SectionRepository, Long> {

}
