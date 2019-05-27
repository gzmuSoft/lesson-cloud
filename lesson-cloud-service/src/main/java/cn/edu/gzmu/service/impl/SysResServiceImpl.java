package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.SysRes;
import cn.edu.gzmu.model.entity.SysRole;
import cn.edu.gzmu.repository.entity.SysResRepository;
import cn.edu.gzmu.service.SysResService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * SysRes Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class SysResServiceImpl extends BaseServiceImpl<SysResRepository, SysRes, Long>
        implements SysResService {

    private final @NonNull SysResRepository sysResRepository;

    @Override
    public List<SysRes> searchByRoles(List<SysRole> roles) {
        return sysResRepository.searchBySysRoleIds(roles.stream()
                .map(SysRole::getId).toArray(Long[]::new));
    }
}
