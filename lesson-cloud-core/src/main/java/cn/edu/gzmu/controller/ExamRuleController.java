package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.constant.LessonResource;
import cn.edu.gzmu.model.entity.ExamRule;
import cn.edu.gzmu.service.ExamRuleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ExamRule Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-28 17:24:38
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping(LessonResource.EXAM_RULE)
public class ExamRuleController extends BaseController<ExamRule, ExamRuleService, Long> {

    private final @NonNull ExamRuleService examRuleService;

    @GetMapping("/exam/{id}")
    public HttpEntity<?> examById(@PathVariable Long id) {
        return ResponseEntity.ok(examRuleService.searchByExamId(id));
    }

}
