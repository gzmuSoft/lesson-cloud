package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.SysRoleRes;
import cn.edu.gzmu.repository.entity.SysRoleResRepository;
import cn.edu.gzmu.service.SysRoleResService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * SysRoleRes Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class SysRoleResServiceImpl extends BaseServiceImpl<SysRoleResRepository, SysRoleRes, Long>
        implements SysRoleResService {

    @Override
    public SysRoleRes completeEntity(SysRoleRes entity) {
        return entity;
    }
}
