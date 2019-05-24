package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.Section;
import cn.edu.gzmu.service.SectionService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Section Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-24 14:18:22
 */
@RepositoryRestController
@RequestMapping("/section/search")
public class SectionController extends BaseController<Section, SectionService, Long> {

}
