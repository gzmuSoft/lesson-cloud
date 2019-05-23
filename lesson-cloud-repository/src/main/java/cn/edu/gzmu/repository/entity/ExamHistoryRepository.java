package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.ExamHistory;
import cn.edu.gzmu.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * ExamHistory Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-23 17:38:13
 */
@RepositoryRestResource(path = "/examHistory")
public interface ExamHistoryRepository extends BaseRepository<ExamHistory, Long> {

}