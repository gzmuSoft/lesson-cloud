package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.Judgement;
import cn.edu.gzmu.service.JudgementService;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Judgement Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-23 17:38:14
 */
@RepositoryRestController
@RequestMapping("/judgement/search")
public class JudgementController extends BaseController<Judgement, JudgementService, Long> {

}
