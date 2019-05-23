package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.Knowledge;
import cn.edu.gzmu.service.KnowledgeService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Knowledge Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-23 17:38:14
 */
@RepositoryRestController
@RequestMapping("/knowledge/search")
public class KnowledgeController extends BaseController<Knowledge, KnowledgeService, Long> {

}
