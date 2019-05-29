package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.SelOptions;
import cn.edu.gzmu.repository.entity.SelOptionsRepository;
import cn.edu.gzmu.service.SelOptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * SelOptions Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class SelOptionsServiceImpl extends BaseServiceImpl<SelOptionsRepository, SelOptions, Long>
        implements SelOptionsService {

    @Override
    public SelOptions completeEntity(SelOptions entity) {
        return entity;
    }
}
