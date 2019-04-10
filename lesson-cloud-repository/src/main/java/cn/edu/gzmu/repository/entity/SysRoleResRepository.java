package cn.edu.gzmu.repository.entity;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * sys_role_res Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-10 14:11:04
 */
@RepositoryRestResource
public interface SysRoleResRepository extends cn.edu.gzmu.repository.BaseRepository<cn.edu.gzmu.model.entity.SysRoleRes, Long> {
}