package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Essay;
import cn.edu.gzmu.repository.entity.EssayRepository;
import cn.edu.gzmu.service.EssayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * Essay Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class EssayServiceImpl extends BaseServiceImpl<EssayRepository, Essay, Long>
        implements EssayService {

    @Override
    public Essay completeEntity(Essay entity) {
        return entity;
    }
}
