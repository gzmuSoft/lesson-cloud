package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.LogicClass;
import cn.edu.gzmu.service.LogicClassService;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * LogicClass Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-23 17:38:14
 */
@RepositoryRestController
@RequestMapping("/logicClass/search")
public class LogicClassController extends BaseController<LogicClass, LogicClassService, Long> {

}
