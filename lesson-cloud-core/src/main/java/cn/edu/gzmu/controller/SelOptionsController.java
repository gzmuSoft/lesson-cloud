package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.SelOptions;
import cn.edu.gzmu.service.SelOptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SelOptions Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-28 10:48:37
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping("/selOptions/search")
public class SelOptionsController extends BaseController<SelOptions, SelOptionsService, Long> {
    private final static String RESOURCE = "/selOptions/search";

}
