package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.MultiSel;
import cn.edu.gzmu.repository.entity.MultiSelRepository;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* MultiSel Controller
*
* @author echo
* @version 1.0
* @date 2019-4-19 22:08:05
*/
@RepositoryRestController
@RequestMapping("/multiSels")
public class MultiSelController extends BaseController<MultiSel, MultiSelRepository, Long> {

}
