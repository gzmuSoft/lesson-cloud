package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.SysRes;
import cn.edu.gzmu.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


/**
 * SysRes Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:05:31
 */
@RepositoryRestResource(path = "/sysReses")
public interface SysResRepository extends BaseRepository<SysRes, Long> {

    /**
     * 通过角色id列表查询所有资源
     *
     * @param ids 角色ids
     * @return 返回值
     */
    @RestResource(path = "sysRoles", rel = "sysRoles", description = @Description("通过角色id列表查询所有资源"))
    @Query(value = "SELECT srr.*, sr.* FROM sys_role_res srr, sys_res sr WHERE srr.role_id in :ids and srr.res_id = sr.id", nativeQuery = true)
    List<SysRes> searchBySysRoleIds(Long[] ids);

}