package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.ExamRule;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


/**
 * ExamRule Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-23 17:38:13
 */
@RepositoryRestResource(path = "examRule")
public interface ExamRuleRepository extends BaseRepository<ExamRule, Long> {

    /**
     * 根据 exam id 查询所有
     *
     * @param id examId
     * @return 结果
     */
    List<ExamRule> findAllByExamId(Long id);
}