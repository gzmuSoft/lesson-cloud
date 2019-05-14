package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.Paper;
import cn.edu.gzmu.service.PaperService;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Paper Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-14 11:36:13
 */
@RepositoryRestController
@RequestMapping("/papers/search")
public class PaperController extends BaseController<Paper, PaperService, Long> {

}
