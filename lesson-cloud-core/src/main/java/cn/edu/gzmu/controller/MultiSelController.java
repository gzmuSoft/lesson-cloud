package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.MultiSel;
import cn.edu.gzmu.service.MultiSelService;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * MultiSel Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-23 17:38:14
 */
@RepositoryRestController
@RequestMapping("/multiSel/search")
public class MultiSelController extends BaseController<MultiSel, MultiSelService, Long> {

}
