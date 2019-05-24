package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.SysRole;
import cn.edu.gzmu.service.SysRoleService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SysRole Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-24 14:18:22
 */
@RepositoryRestController
@RequestMapping("/sysRole/search")
public class SysRoleController extends BaseController<SysRole, SysRoleService, Long> {

}
