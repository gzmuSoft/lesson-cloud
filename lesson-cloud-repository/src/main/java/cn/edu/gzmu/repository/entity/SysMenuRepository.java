package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.SysMenu;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
@RepositoryRestResource(path = "sysMenu")
public interface SysMenuRepository extends BaseRepository<SysMenu, Long> {

    /**
     * 通过角色id列表查询所有资源
     *
     * @param names 角色名称
     * @return 返回值
     */
    @RestResource(path = "sysRole", rel = "sysRole", description = @Description("通过角色名称列表查询所有菜单"))
    @Query(value = "SELECT sm.* FROM sys_role_menu srm, sys_menu sm WHERE srm.role_name in :names and srm.menu_id = sm.id " +
            "and sm.is_enable = 1", nativeQuery = true)
    List<SysMenu> searchBySysRoleNames(@Param("names") List<String> names);

}