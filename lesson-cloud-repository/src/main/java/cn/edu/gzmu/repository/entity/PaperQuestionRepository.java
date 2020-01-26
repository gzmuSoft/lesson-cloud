package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.constant.QuestionType;
import cn.edu.gzmu.model.entity.KnowledgeQuestion;
import cn.edu.gzmu.model.entity.PaperQuestion;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * @author BugRui
 * @date 2020/1/21 下午9:11
 **/
@RepositoryRestResource(path = "PaperQuestion")
public interface PaperQuestionRepository extends BaseRepository<PaperQuestion, Long> {
    /**
     * 根据paper id和问题类型查询 paper关联
     *
     * @param paperId      试卷 id
     * @param questionType 问题类型
     * @return j结果
     */
    List<PaperQuestion> findAllByPaperIdAndQuestionType(Long paperId, QuestionType questionType);

    /**
     * 根据paper 查询关联
     *
     * @param paperId 试卷 id
     * @return 结果
     */
    List<PaperQuestion> findAllByPaperId(Long paperId);
}
