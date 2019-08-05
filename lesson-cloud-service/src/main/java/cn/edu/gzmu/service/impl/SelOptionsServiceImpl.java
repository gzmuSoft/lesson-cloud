package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.constant.QuestionType;
import cn.edu.gzmu.model.entity.SelOptions;
import cn.edu.gzmu.repository.entity.MultiSelRepository;
import cn.edu.gzmu.repository.entity.SelOptionsRepository;
import cn.edu.gzmu.repository.entity.SingleSelRepository;
import cn.edu.gzmu.service.SelOptionsService;
import lombok.NonNull;
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
    private final @NonNull SingleSelRepository singleSelRepository;
    private final @NonNull MultiSelRepository multiSelRepository;

    @Override
    protected SelOptions completeEntity(SelOptions entity) {
        if(QuestionType.isSingleSel(entity.getType())){
            entity.setSingleSel(singleSelRepository.findById(entity.getQuestionId()).orElse(null));
        }else if(QuestionType.isMultiSel(entity.getType())){
            entity.setMultiSel(multiSelRepository.findById(entity.getQuestionId()).orElse(null));
        }
        return entity;
    }
}
