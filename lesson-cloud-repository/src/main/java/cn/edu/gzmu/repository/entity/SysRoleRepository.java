package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.SysRole;
import cn.edu.gzmu.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


/**
 * SysRole Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:05:31
 */
@RepositoryRestResource(path = "/sysRoles")
public interface SysRoleRepository extends BaseRepository<SysRole, Long> {

    /**
     * 通过用户id查询所有角色
     *
     * @param id id
     * @return SysRoles
     */
    @RestResource(path = "sysUser", rel = "sysUser", description = @Description("通过用户id查询所有角色"))
    @Query(value = "SELECT r.* FROM sys_role r, sys_user_role ur WHERE ur.user_id = :id AND ur.role_id = r.id AND ur.is_enable = 1 AND r.is_enable = 1 ", nativeQuery = true)
    List<SysRole> searchBySysUserId(@Param("id") Long id);

    /**
     * 通过资源id查询所有角色
     *
     * @param id id
     * @return SysRoles
     */
    @RestResource(path = "sysRes", rel = "sysRes", description = @Description("通过用户id查询所有角色"))
    @Query(value = "SELECT r.* FROM sys_role r, sys_role_res rr WHERE rr.res_id = :id AND rr.role_id = r.id AND rr.is_enable = 1 AND r.is_enable = 1 ", nativeQuery = true)
    List<SysRole> searchBySysResId(@Param("id") Long id);

}