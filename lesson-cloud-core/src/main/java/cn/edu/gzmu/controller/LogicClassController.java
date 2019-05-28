package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.LogicClass;
import cn.edu.gzmu.service.LogicClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * LogicClass Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-28 10:48:37
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping("/logicClass/search")
public class LogicClassController extends BaseController<LogicClass, LogicClassService, Long> {
    private final static String RESOURCE = "/logicClass/search";

}
