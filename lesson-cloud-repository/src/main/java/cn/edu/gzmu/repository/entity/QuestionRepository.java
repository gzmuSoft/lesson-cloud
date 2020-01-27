package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.constant.QuestionType;
import cn.edu.gzmu.model.entity.Question;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Set;

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
     * @param ids question ids
     * @return question list
     */
    List<Question> findAllByIdIn(List<Long> ids);

    /**
     * 根据ids 查出Set
     *
     * @param ids id list
     * @return 结果
     */
    Set<Question> findDistinctByIdIn(List<Long> ids);

    /**
     * 随机简单生成试题
     *
     * @param ids                待选择的问题
     * @param lim                questionCount-requireQuestionCount
     * @param requireIds         必选问题
     * @param questionType       选题类型
     * @param startDifficultRate 选题起始难度
     * @param endDifficultRate   选题终止难度
     * @return question List
     */

    @Query(value = "(select * from question  where ( is_enable = 1) and (id in (:ids)) and (id not in  (:requireIds)) and type=:questionType and (difficult_rate between :startDifficultRate and :endDifficultRate) limit :lim) union all (select * from question  where id in (:requireIds))  order by rand()", nativeQuery = true)
    List<Question> simpleGenerateQuestion(@Param("ids") List<Long> ids, @Param("lim") Integer lim, @Param("requireIds") List<Long> requireIds, @Param("questionType") Integer questionType,
                                          @Param("startDifficultRate") Integer startDifficultRate, @Param("endDifficultRate") Integer endDifficultRate);

    /**
     * 随机简单生成试题
     *
     * @param ids                select ids
     * @param lim                数量
     * @param questionType       问题类型
     * @param startDifficultRate 起始难度
     * @param endDifficultRate   终止难度
     * @return 结果
     */
    @Query(value = "(select * from question  where ( is_enable = 1) and (id in (:ids)) and type=:questionType and (difficult_rate between :startDifficultRate and :endDifficultRate) limit :lim)order by rand()", nativeQuery = true)
    List<Question> simpleGenerateQuestion(@Param("ids") List<Long> ids, @Param("lim") Integer lim, @Param("questionType") Integer questionType,
                                          @Param("startDifficultRate") Integer startDifficultRate, @Param("endDifficultRate") Integer endDifficultRate);
}
