package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.ExamRule;
import cn.edu.gzmu.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * ExamRule Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:05:30
 */
@RepositoryRestResource(path = "/examRules")
public interface ExamRuleRepository extends BaseRepository<ExamRule, Long> {

}