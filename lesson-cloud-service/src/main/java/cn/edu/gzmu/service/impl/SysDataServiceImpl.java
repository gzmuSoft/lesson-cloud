package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.SysData;
import cn.edu.gzmu.repository.entity.SysDataRepository;
import cn.edu.gzmu.service.SysDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * SysData Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class SysDataServiceImpl extends BaseServiceImpl<SysDataRepository, SysData, Long>
        implements SysDataService {

    @Override
    public SysData completeEntity(SysData entity) {
        return entity;
    }
}
