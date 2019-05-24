package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.Paper;
import cn.edu.gzmu.service.PaperService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Paper Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-24 14:18:22
 */
@RepositoryRestController
@RequestMapping("/paper/search")
public class PaperController extends BaseController<Paper, PaperService, Long> {

}
