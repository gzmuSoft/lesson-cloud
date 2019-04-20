package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.SingleSel;
import cn.edu.gzmu.repository.entity.SingleSelRepository;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* SingleSel Controller
*
* @author echo
* @version 1.0
* @date 2019-4-20 0:08:37
*/
@RepositoryRestController
@RequestMapping("/singleSels")
public class SingleSelController extends BaseController<SingleSel, SingleSelRepository, Long> {

}
