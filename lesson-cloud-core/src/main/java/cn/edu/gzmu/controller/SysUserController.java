package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.SysUser;
import cn.edu.gzmu.repository.entity.SysUserRepository;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* SysUser Controller
*
* @author echo
* @version 1.0
* @date 19-3-25 14:51
*/
@RepositoryRestController
@RequestMapping("/sysUsers")
public class SysUserController extends BaseController<SysUser, SysUserRepository, Long> {

}
