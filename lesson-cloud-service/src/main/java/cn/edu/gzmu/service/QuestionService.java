package cn.edu.gzmu.service;

import cn.edu.gzmu.model.entity.Knowledge;
import cn.edu.gzmu.model.entity.Question;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Set;

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/19 下午4:28
 */
public interface QuestionService extends BaseService<Question, Long> {

    /**
     * 根据知识点 ids 和 ids 的数量查询含有这些知识点的题目的 id.
     *
     * @param ids 知识点 id 列表
     * @return 包含这些知识点的题目
     */
    List<Question> getQuestionIdByKnowledgeIds(List<Long> ids);

    /**
     * 根据章 ids 查询属于这些章的题目.
     *
     * @param ids 章 id 列表
     * @return 属于这些节的题目
     */
    List<Question> getQuestionBySectionIds(List<Long> ids);

    /**
     * 根据节 ids 查询属于这些节的题目.
     *
     * @param ids 节 id 列表
     * @return 属于这些节的题目
     */
    List<Question> getQuestionByPassageIds(List<Long> ids);

    /**
     * 根据题目 id 查询关联的章、节、知识点 ids
     *
     * @param id 题目id
     * @return 结果.
     */
    JSONObject getQuestionCorrelationById(Long id);

    /**
     * 根据题目 id 查询关联的知识点
     *
     * @param id 题目id
     * @return 结果.
     */
    Set<Knowledge> getKnowledgeSetById(Long id);

    /**
     * 保存/修改题目.
     *
     * @param ids      知识点 ids
     * @param question 题目对象
     */
    void saveOrUpdateQuestion(List<Long> ids, Question question);

    /**
     * 删除题目并解除关联.
     *
     * @param id 题目 id
     */
    void deleteQuestion(Long id);
}
