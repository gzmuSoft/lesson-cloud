package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.KnowledgeQuestion;
import cn.edu.gzmu.model.entity.PaperQuestion;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author BugRui
 * @date 2020/1/21 下午9:11
 **/
@RepositoryRestResource(path = "PaperQuestion")
public interface PaperQuestionRepository extends BaseRepository<PaperQuestion, Long> {
}
