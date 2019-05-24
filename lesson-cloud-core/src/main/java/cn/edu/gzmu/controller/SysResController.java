package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.SysRes;
import cn.edu.gzmu.service.SysResService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SysRes Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-24 14:18:22
 */
@RepositoryRestController
@RequestMapping("/sysRes/search")
public class SysResController extends BaseController<SysRes, SysResService, Long> {

}
