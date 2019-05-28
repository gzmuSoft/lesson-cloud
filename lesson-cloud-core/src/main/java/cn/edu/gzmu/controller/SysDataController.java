package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.SysData;
import cn.edu.gzmu.service.SysDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SysData Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-28 10:48:37
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping("/sysData/search")
public class SysDataController extends BaseController<SysData, SysDataService, Long> {
    private final static String RESOURCE = "/sysData/search";

}
