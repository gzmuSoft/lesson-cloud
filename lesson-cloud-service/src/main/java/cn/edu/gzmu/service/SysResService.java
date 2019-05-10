package cn.edu.gzmu.service;

import cn.edu.gzmu.model.entity.SysRes;
import cn.edu.gzmu.model.entity.SysRole;

import java.util.List;


/**
 * SysRes Service
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
public interface SysResService extends BaseService<SysRes, Long> {

    /**
     * 通过所有角色查询所有的资源
     *
     * @param roles 用户名
     * @return 资源
     */
    List<SysRes> searchByRoles(List<SysRole> roles);
}
