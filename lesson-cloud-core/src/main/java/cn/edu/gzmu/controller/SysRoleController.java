package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.SysRole;
import cn.edu.gzmu.repository.entity.SysRoleRepository;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* SysRole Controller
*
* @author echo
* @version 1.0
* @date 2019-4-19 22:08:05
*/
@RepositoryRestController
@RequestMapping("/sysRoles")
public class SysRoleController extends BaseController<SysRole, SysRoleRepository, Long> {

}
