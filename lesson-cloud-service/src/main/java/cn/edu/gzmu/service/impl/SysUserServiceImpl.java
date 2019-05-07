package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.SysUser;
import cn.edu.gzmu.repository.entity.SysUserRepository;
import cn.edu.gzmu.service.SysUserService;
import org.springframework.stereotype.Service;


/**
 * SysUser Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserRepository, SysUser, Long>
        implements SysUserService {

}
