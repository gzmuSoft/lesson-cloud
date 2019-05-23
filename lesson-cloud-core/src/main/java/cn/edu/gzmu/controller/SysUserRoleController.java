package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.SysUserRole;
import cn.edu.gzmu.service.SysUserRoleService;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SysUserRole Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-23 17:38:14
 */
@RepositoryRestController
@RequestMapping("/sysUserRole/search")
public class SysUserRoleController extends BaseController<SysUserRole, SysUserRoleService, Long> {

}
