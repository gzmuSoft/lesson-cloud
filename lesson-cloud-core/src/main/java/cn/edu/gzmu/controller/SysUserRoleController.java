package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.SysUserRole;
import cn.edu.gzmu.repository.entity.SysUserRoleRepository;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* SysUserRole Controller
*
* @author echo
* @version 1.0
* @date 2019-4-19 22:08:05
*/
@RepositoryRestController
@RequestMapping("/sysUserRoles")
public class SysUserRoleController extends BaseController<SysUserRole, SysUserRoleRepository, Long> {

}
