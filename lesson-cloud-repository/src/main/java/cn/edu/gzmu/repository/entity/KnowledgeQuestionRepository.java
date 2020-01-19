package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.ExamRule;
import cn.edu.gzmu.model.entity.KnowledgeQuestion;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/19 下午4:25
 */
@RepositoryRestResource(path = "knowledgeQuestion")
public interface KnowledgeQuestionRepository extends BaseRepository<KnowledgeQuestion, Long> {

    /**
     * 根据 question id 查询所有
     *
     * @param id questionId
     * @return 结果
     */
    List<KnowledgeQuestion> findAllByQuestionId(Long id);

    /**
     * 根据 knowledge id 查询所有
     *
     * @param id knowledgeId
     * @return 结果
     */
    List<KnowledgeQuestion> findAllByKnowledgeId(Long id);

    /**
     * 根据 knowledge id 查询所有
     *
     * @param ids knowledgeId
     * @return 结果
     */
    List<KnowledgeQuestion> findAllByKnowledgeIdIn(List<Long> ids);


}
