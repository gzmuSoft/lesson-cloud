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
    List<Question> getQuestionIdByKnowledgeId(List<Long> ids);
}
