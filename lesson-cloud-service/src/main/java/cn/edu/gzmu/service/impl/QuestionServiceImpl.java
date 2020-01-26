package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Question;
import cn.edu.gzmu.repository.entity.KnowledgeQuestionRepository;
import cn.edu.gzmu.repository.entity.QuestionRepository;
import cn.edu.gzmu.service.QuestionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/19 下午4:28
 */
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl extends BaseServiceImpl<QuestionRepository, Question, Long>
        implements QuestionService {

    private final @NonNull KnowledgeQuestionRepository knowledgeQuestionRepository;
    private final @NonNull QuestionRepository questionRepository;

    /**
     * 根据知识点 ids 和 ids 的数量查询含有这些知识点的题目的 id.
     *
     * @param ids 知识点 id 列表
     * @return .
     */
    @Override
    public List<Question> getQuestionIdByKnowledgeId(List<Long> ids) {
        List<Long> questionIds = knowledgeQuestionRepository.getQuestionIdByKnowledgeId(ids, ids.size());
        return questionRepository.findAllByIdIn(questionIds);
    }
}
