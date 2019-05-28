package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.ExamHistory;
import cn.edu.gzmu.service.ExamHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ExamHistory Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-28 10:48:37
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping("/examHistory/search")
public class ExamHistoryController extends BaseController<ExamHistory, ExamHistoryService, Long> {
    private final static String RESOURCE = "/examHistory/search";

}
