package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.Knowledge;
import cn.edu.gzmu.repository.entity.KnowledgeRepository;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* Knowledge Controller
*
* @author echo
* @version 1.0
* @date 2019-4-19 22:08:05
*/
@RepositoryRestController
@RequestMapping("/knowledges")
public class KnowledgeController extends BaseController<Knowledge, KnowledgeRepository, Long> {

}
