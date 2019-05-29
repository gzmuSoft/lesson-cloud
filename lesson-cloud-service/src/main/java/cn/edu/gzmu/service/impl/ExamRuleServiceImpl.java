package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.ExamRule;
import cn.edu.gzmu.repository.entity.ExamRepository;
import cn.edu.gzmu.repository.entity.ExamRuleRepository;
import cn.edu.gzmu.service.ExamRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.NonNull;


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

    private final @NonNull ExamRuleRepository examRuleRepository;
    private final @NonNull ExamRepository examRepository;

    @Override
    public Page<ExamRule> searchAll(Pageable pageable) {
        return examRuleRepository.findAll(pageable).map(examRule -> {
            if (examRule.getExamId() != null){
                examRule.setExam(examRepository.getOne(examRule.getExamId()));
            }
            return examRule;
        });
    }
}
