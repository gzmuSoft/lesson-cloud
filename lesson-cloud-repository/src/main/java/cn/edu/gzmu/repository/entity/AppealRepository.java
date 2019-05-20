package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.Appeal;
import cn.edu.gzmu.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Appeal Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-20 9:18:57
 */
@RepositoryRestResource(path = "/appeals")
public interface AppealRepository extends BaseRepository<Appeal, Long> {

}