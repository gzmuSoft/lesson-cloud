package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.ExamRule;
import cn.edu.gzmu.repository.entity.ExamRepository;
import cn.edu.gzmu.repository.entity.ExamRuleRepository;
import cn.edu.gzmu.service.ExamRuleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * ExamRule Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class ExamRuleServiceImpl extends BaseServiceImpl<ExamRuleRepository, ExamRule, Long>
        implements ExamRuleService {

    private final @NonNull ExamRepository examRepository;

    @Override
    public ExamRule completeEntity(ExamRule entity) {
        entity.setExam(examRepository.getOne(entity.getExamId()));
        return entity;
    }

}
