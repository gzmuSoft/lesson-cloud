package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.SysRole;
import cn.edu.gzmu.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * SysRole Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-19 22:08:04
 */
@RepositoryRestResource
public interface SysRoleRepository extends BaseRepository<SysRole, Long> {

}