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
     * @return SysRoles
     */
    @RestResource(path = "sysRoles", rel = "sysRoles", description = @Description("通过角色id查询"))
    @Query(value = "SELECT r.* FROM sys_role_res rs, sys_res r WHERE rs.role_id in :ids AND rs.res_id = r.id AND rs.is_enable = 1 AND r.is_enable = 1 ", nativeQuery = true)
    Set<SysRes> searchBySysRoleIds(@Param("ids") List<Long> ids);


}