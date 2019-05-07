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
 * @date 2019-5-7 11:33:57
 */
@RepositoryRestController
@RequestMapping("/sysRoleReses")
public class SysRoleResController extends BaseController<SysRoleRes, SysRoleResService, Long> {

}
