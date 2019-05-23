package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.ExamRule;
import cn.edu.gzmu.service.ExamRuleService;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ExamRule Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-23 17:38:14
 */
@RepositoryRestController
@RequestMapping("/examRule/search")
public class ExamRuleController extends BaseController<ExamRule, ExamRuleService, Long> {

}
