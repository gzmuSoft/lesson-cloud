package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.SysUser;
import cn.edu.gzmu.service.SysUserService;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SysUser Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-20 9:18:57
 */
@RepositoryRestController
@RequestMapping("/sysUsers/search")
public class SysUserController extends BaseController<SysUser, SysUserService, Long> {

}
