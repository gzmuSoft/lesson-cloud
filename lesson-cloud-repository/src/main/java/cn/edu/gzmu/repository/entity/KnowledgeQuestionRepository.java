package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.KnowledgeQuestion;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/19 下午4:25
 */
@RepositoryRestResource(path = "knowledgeQuestion")
public interface KnowledgeQuestionRepository extends BaseRepository<KnowledgeQuestion, Long> {
}
