package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.constant.LessonResource;
import cn.edu.gzmu.model.entity.Question;
import cn.edu.gzmu.service.QuestionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/19 下午4:30
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping(LessonResource.QUESTION_SEARCH)
public class QuestionController extends BaseController<Question, QuestionService, Long>{

    private final @NonNull QuestionService questionService;

    /**
     * 根据知识点 ids 和 ids 的数量查询含有这些知识点的题目的 id.
     *
     * @param ids 知识点 id 列表
     * @return .
     */
    @GetMapping("/knowledge")
    public HttpEntity<?> getQuestionIdByKnowledgeId(@RequestParam("id") List<Long> ids) {
        return ResponseEntity.ok(questionService.getQuestionIdByKnowledgeId(ids));
    }
}
