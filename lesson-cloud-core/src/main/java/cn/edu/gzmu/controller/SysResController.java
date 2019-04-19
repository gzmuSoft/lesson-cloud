package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.SysRes;
import cn.edu.gzmu.repository.entity.SysResRepository;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* SysRes Controller
*
* @author echo
* @version 1.0
* @date 2019-4-19 22:08:05
*/
@RepositoryRestController
@RequestMapping("/sysReses")
public class SysResController extends BaseController<SysRes, SysResRepository, Long> {

}
