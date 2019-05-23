package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.SysLog;
import cn.edu.gzmu.service.SysLogService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SysLog Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-23 17:38:14
 */
@RepositoryRestController
@RequestMapping("/sysLog/search")
public class SysLogController extends BaseController<SysLog, SysLogService, Long> {

}
