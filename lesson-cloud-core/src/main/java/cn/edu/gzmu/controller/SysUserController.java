package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.SysUser;
import cn.edu.gzmu.service.SysUserService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SysUser Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-23 17:38:14
 */
@RepositoryRestController
@RequestMapping("/sysUser/search")
public class SysUserController extends BaseController<SysUser, SysUserService, Long> {

}
