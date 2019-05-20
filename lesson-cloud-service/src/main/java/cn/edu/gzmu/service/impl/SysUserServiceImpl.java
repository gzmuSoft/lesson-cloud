package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.SysUser;
import cn.edu.gzmu.model.exception.UserNotFoundException;
import cn.edu.gzmu.repository.entity.SysUserRepository;
import cn.edu.gzmu.service.SysUserService;
import org.springframework.data.jpa.domain.Specification;
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

    private final SysUserRepository sysUserRepository;

    public SysUserServiceImpl(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    @Override
    public SysUser searchByAll(String user) {
        return
                sysUserRepository.findOne((Specification<SysUser>)
                        (root, query, criteriaBuilder) ->
                                criteriaBuilder.or(
                                        criteriaBuilder.equal(root.get("name"), user),
                                        criteriaBuilder.equal(root.get("email"), user),
                                        criteriaBuilder.equal(root.get("phone"), user)
                                )).orElseThrow(() ->
                        new UserNotFoundException(String.format("用户 %s 不存在", user)));
    }

    @Override
    public SysUser searchByPhone(String phone) {
        return
                sysUserRepository.findFirstByPhone(phone).orElseThrow(() ->
                        new UserNotFoundException(String.format("用户 %s 不存在", phone)));
    }
}
