package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.SelOptions;
import cn.edu.gzmu.repository.entity.SelOptionsRepository;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* SelOptions Controller
*
* @author echo
* @version 1.0
* @date 2019-4-19 22:08:05
*/
@RepositoryRestController
@RequestMapping("/selOptionses")
public class SelOptionsController extends BaseController<SelOptions, SelOptionsRepository, Long> {

}
