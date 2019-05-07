package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.SysData;
import cn.edu.gzmu.service.SysDataService;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* SysData Controller
*
* @author echo
* @version 1.0
* @date 2019-5-7 11:05:31
*/
@RepositoryRestController
@RequestMapping("/sysDatas")
public class SysDataController extends BaseController<SysData, SysDataService, Long> {

}
