package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.PaperDetail;
import cn.edu.gzmu.service.PaperDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * PaperDetail Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-28 10:48:37
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping("/paperDetail/search")
public class PaperDetailController extends BaseController<PaperDetail, PaperDetailService, Long> {
    private final static String RESOURCE = "/paperDetail/search";

}
