package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.Appeal;
import cn.edu.gzmu.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Appeal Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-23 17:38:13
 */
@RepositoryRestResource(path = "/appeal")
public interface AppealRepository extends BaseRepository<Appeal, Long> {

}