package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.Essay;
import cn.edu.gzmu.service.EssayService;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Essay Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-20 9:18:57
 */
@RepositoryRestController
@RequestMapping("/essays/search")
public class EssayController extends BaseController<Essay, EssayService, Long> {

}
