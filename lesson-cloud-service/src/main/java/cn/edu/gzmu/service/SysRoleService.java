package cn.edu.gzmu.service;

import cn.edu.gzmu.model.entity.SysRole;

import java.util.List;


/**
 * SysRole Service
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
public interface SysRoleService extends BaseService<SysRole, Long> {

    /**
     * 通过用户名查询所有角色
     *
     * @param username 用户名
     * @return 所有角色
     */
    List<SysRole> searchByUsername(String username);
}
