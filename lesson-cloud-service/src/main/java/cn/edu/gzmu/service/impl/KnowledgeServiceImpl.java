package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Knowledge;
import cn.edu.gzmu.repository.entity.KnowledgeRepository;
import cn.edu.gzmu.service.KnowledgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * Knowledge Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class KnowledgeServiceImpl extends BaseServiceImpl<KnowledgeRepository, Knowledge, Long>
        implements KnowledgeService {

    @Override
    public Knowledge completeEntity(Knowledge entity) {
        return entity;
    }
}
