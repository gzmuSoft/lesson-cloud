package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.ExamRule;
import cn.edu.gzmu.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * ExamRule Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-23 17:38:13
 */
@RepositoryRestResource(path = "/examRule")
public interface ExamRuleRepository extends BaseRepository<ExamRule, Long> {

}