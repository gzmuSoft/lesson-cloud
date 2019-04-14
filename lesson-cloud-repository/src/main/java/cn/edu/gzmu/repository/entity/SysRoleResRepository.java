package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.SysRoleRes;
import cn.edu.gzmu.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * SysRoleRes Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-12 20:32:46
 */
@RepositoryRestResource
public interface SysRoleResRepository extends BaseRepository<SysRoleRes, Long> {

}