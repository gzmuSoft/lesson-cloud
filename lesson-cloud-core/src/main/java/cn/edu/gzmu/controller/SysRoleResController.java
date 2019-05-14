package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.SysRoleRes;
import cn.edu.gzmu.service.SysRoleResService;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SysRoleRes Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-14 11:36:13
 */
@RepositoryRestController
@RequestMapping("/sysRoleReses/search")
public class SysRoleResController extends BaseController<SysRoleRes, SysRoleResService, Long> {

}
