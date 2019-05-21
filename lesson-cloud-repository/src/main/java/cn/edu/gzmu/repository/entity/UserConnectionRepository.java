package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.UserConnection;
import cn.edu.gzmu.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * UserConnection Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-20 9:18:57
 */
@RepositoryRestResource(path = "/userConnections")
public interface UserConnectionRepository extends BaseRepository<UserConnection, Long> {

}