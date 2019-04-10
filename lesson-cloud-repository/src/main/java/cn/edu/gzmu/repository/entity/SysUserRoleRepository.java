package cn.edu.gzmu.repository.entity;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * sys_user_role Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-10 14:11:05
 */
@RepositoryRestResource
public interface SysUserRoleRepository extends cn.edu.gzmu.repository.BaseRepository<cn.edu.gzmu.model.entity.SysUserRole, Long> {
}