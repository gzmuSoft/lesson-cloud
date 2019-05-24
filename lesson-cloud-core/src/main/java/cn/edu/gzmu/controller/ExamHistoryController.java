package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.ExamHistory;
import cn.edu.gzmu.service.ExamHistoryService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ExamHistory Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-24 14:18:22
 */
@RepositoryRestController
@RequestMapping("/examHistory/search")
public class ExamHistoryController extends BaseController<ExamHistory, ExamHistoryService, Long> {

}
