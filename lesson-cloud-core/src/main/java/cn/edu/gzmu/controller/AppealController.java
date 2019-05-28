package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.Appeal;
import cn.edu.gzmu.service.AppealService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Appeal Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-28 10:48:37
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping("/appeal/search")
public class AppealController extends BaseController<Appeal, AppealService, Long> {
    private final static String RESOURCE = "/appeal/search";

}
