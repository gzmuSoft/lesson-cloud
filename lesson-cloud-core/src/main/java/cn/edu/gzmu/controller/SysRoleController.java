package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.SysRole;
import cn.edu.gzmu.service.SysRoleService;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SysRole Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-14 11:36:13
 */
@RepositoryRestController
@RequestMapping("/sysRoles/search")
public class SysRoleController extends BaseController<SysRole, SysRoleService, Long> {

}
