package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.annoection.VerifyParameter;
import cn.edu.gzmu.model.constant.LessonResource;
import cn.edu.gzmu.model.entity.Question;
import cn.edu.gzmu.service.QuestionService;
import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
public class QuestionController extends BaseController<Question, QuestionService, Long> {

    private final @NonNull QuestionService questionService;

    /**
     * 根据知识点 ids 和 ids 的数量查询含有这些知识点的题目的 id.
     *
     * @param ids 知识点 id 列表
     * @return 包含这些知识点的题目.
     */
    @GetMapping("/knowledge")
    public HttpEntity<?> getQuestionByKnowledgeIds(@RequestParam() List<Long> ids) {
        return ResponseEntity.ok(questionService.getQuestionIdByKnowledgeIds(ids));
    }

    /**
     * 根据章 ids 查询属于这些章的题目.
     *
     * @param ids 知识点 id 列表
     * @return 属于这些章的题目.
     */
    @GetMapping("/section")
    public HttpEntity<?> getQuestionBySectionIds(@RequestParam() List<Long> ids) {
        return ResponseEntity.ok(questionService.getQuestionBySectionIds(ids));
    }

    /**
     * 根据节 ids 查询属于这些节的题目.
     *
     * @param ids 知识点 id 列表
     * @return 属于这些节的题目.
     */
    @GetMapping("/passage")
    public HttpEntity<?> getQuestionByPassageIds(@RequestParam() List<Long> ids) {
        return ResponseEntity.ok(questionService.getQuestionByPassageIds(ids));
    }

    /**
     * 根据题目 id  查询关联的章、节、知识点 ids.
     *
     * @param id 题目id
     * @return 结果.
     */
    @GetMapping("/allCorrelation/{id}")
    public HttpEntity<?> getQuestionCorrelationById(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.getQuestionCorrelationById(id));
    }

    /**
     * 根据题目 id 查询关联的知识点
     *
     * @param id 题目ide
     * @return 结果.
     */
    @GetMapping("/knowledgeCorrelation/{id}")
    public HttpEntity<?> getKnowledgeSetById(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.getKnowledgeSetById(id));
    }

    /**
     * 保存/修改题目.
     *
     * @param data 数据对象
     * @return .
     */
    @PostMapping("/question")
    @VerifyParameter(required = {"ids#知识点id列表为必填项", "question#题目为必填项"})
    public HttpEntity<?> saveOrUpdateQuestion(@NotNull @RequestBody JSONObject data) {
        questionService.saveOrUpdateQuestion(
                data.getJSONArray("ids").toJavaList(Long.class),
                data.getObject("question", Question.class));
        return ResponseEntity.ok().build();
    }

    /**
     * 删除题目并解除关联.
     *
     * @param id 题目 id
     */
    @PatchMapping("/question/{id}")
    public HttpEntity<?> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.ok().build();
    }

}
