package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.SysRoleMenu;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


/**
 * SysRoleMenu Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:05:31
 */
@RepositoryRestResource(path = "sysRoleMenu")
public interface SysRoleMenuRepository extends BaseRepository<SysRoleMenu, Long> {

    /**
     * 通过 角色名称 查询
     *
     * @param roleName 角色名称
     * @return 结果
     */
    List<SysRoleMenu> findAllByRoleName(String roleName);

}