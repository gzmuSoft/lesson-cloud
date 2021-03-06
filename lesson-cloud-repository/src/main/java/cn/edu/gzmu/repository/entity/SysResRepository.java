package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.SysRes;
import cn.edu.gzmu.repository.base.BaseRepository;
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
@RepositoryRestResource(path = "sysRes")
public interface SysResRepository extends BaseRepository<SysRes, Long> {

    /**
     * 通过角色id列表查询所有资源
     *
     * @param names 角色名称
     * @return 返回值
     */
    @RestResource(path = "sysRole", rel = "sysRole", description = @Description("通过角色名称查询所有资源"))
    @Query(value = "SELECT srr.*, sr.* FROM sys_role_res srr, sys_res sr WHERE srr.role_name in :names and srr.res_id = sr.id " +
            "and sr.is_enable = 1", nativeQuery = true)
    List<SysRes> searchBySysRoleNames(String[] names);

}