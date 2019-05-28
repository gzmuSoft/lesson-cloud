package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.Knowledge;
import cn.edu.gzmu.service.KnowledgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Knowledge Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-28 10:48:37
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping("/knowledge/search")
public class KnowledgeController extends BaseController<Knowledge, KnowledgeService, Long> {
    private final static String RESOURCE = "/knowledge/search";

}
