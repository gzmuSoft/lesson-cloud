package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.ExamHistory;
import cn.edu.gzmu.service.ExamHistoryService;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ExamHistory Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-14 11:36:13
 */
@RepositoryRestController
@RequestMapping("/examHistories/search")
public class ExamHistoryController extends BaseController<ExamHistory, ExamHistoryService, Long> {

}
