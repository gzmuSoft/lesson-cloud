package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.SingleSel;
import cn.edu.gzmu.service.SingleSelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SingleSel Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-28 10:48:37
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping("/singleSel/search")
public class SingleSelController extends BaseController<SingleSel, SingleSelService, Long> {
    private final static String RESOURCE = "/singleSel/search";

}
