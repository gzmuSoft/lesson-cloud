package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.Question;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * question.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/19 下午4:24
 */
@RepositoryRestResource(path = "question")
public interface QuestionRepository extends BaseRepository<Question, Long> {
    /**
     * 根据ids  查出所有的question
     *
     * @param ids
     * @return
     */
    List<Question> findAllByIdIn(List<Long> ids);
}
