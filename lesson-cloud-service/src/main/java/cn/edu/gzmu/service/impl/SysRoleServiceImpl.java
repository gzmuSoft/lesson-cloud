package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.SysRole;
import cn.edu.gzmu.model.entity.SysUser;
import cn.edu.gzmu.model.exception.UserNotFoundException;
import cn.edu.gzmu.repository.entity.SysRoleRepository;
import cn.edu.gzmu.repository.entity.SysUserRepository;
import cn.edu.gzmu.service.SysRoleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * SysRole Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleRepository, SysRole, Long>
        implements SysRoleService {

    private final @NonNull SysRoleRepository sysRoleRepository;
    private final @NonNull SysUserRepository sysUserRepository;

    @Override
    public List<SysRole> searchByUsername(String username) {
        SysUser user =
                sysUserRepository.findFirstByName(username).orElseThrow(() ->
                        new UserNotFoundException(String.format("The user %s not found!", username)));
        return sysRoleRepository.searchBySysUserId(user.getId());
    }

    @Override
    public SysRole completeEntity(SysRole entity) {
        return entity;
    }
}
