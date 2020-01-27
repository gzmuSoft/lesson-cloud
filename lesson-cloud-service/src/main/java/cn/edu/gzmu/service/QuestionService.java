package cn.edu.gzmu.service;

import cn.edu.gzmu.model.entity.Question;

import java.util.List;

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
}
