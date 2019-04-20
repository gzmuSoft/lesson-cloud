package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.SysRes;
import cn.edu.gzmu.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Set;


/**
 * SysRes Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-20 0:08:37
 */
@RepositoryRestResource
public interface SysResRepository extends BaseRepository<SysRes, Long> {

    /**
     * 通过角色id查询
     *
     * @param id id
     * @return SysRoles
     */
    @RestResource(path = "sysRole", rel = "sysRole", description = @Description("通过角色id查询"))
    @Query(value = "SELECT r.* FROM sys_role_res rs, sys_res r WHERE rs.role_id = :id AND rs.res_id = r.id AND rs.is_enable = 1 AND r.is_enable = 1 ", nativeQuery = true)
    List<SysRes> searchBySysRoleId(@Param("id") Long id);

    /**
     * 通过角色id查询
     *
     * @param ids ids
     * @return SysRes
     */
    @RestResource(path = "sysRoles", rel = "sysRoles", description = @Description("通过角色id查询"))
    @Query(value = "SELECT r.* FROM sys_role_res rs, sys_res r WHERE rs.role_id in :ids AND rs.res_id = r.id AND rs.is_enable = 1 AND r.is_enable = 1 ", nativeQuery = true)
    Set<SysRes> searchBySysRoleIds(@Param("ids") List<Long> ids);

    /**
     * 通过用户 id 查询它已有的所有资源权限
     *
     * @param id id
     * @return SysRes
     */
    @RestResource(path = "sysUser", rel = "sysUser", description = @Description("通过用户id查询已有权限"))
    @Query(value = "SELECT res.* " +
            "FROM sys_role role, sys_user_role ur, sys_role_res rr, sys_res res, sys_user user " +
            "where ur.user_id = :id AND ur.role_id = role.id AND role.id = rr.role_id AND rr.res_id = res.id " +
            "  AND role.is_enable = 1 AND ur.is_enable = 1 AND user.is_enable = 1 AND rr.is_enable = 1 AND res.is_enable = 1",
            nativeQuery = true)
    Set<SysRes> searchByUserId(@Param("id") Long id);

}