package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.ExamRule;
import cn.edu.gzmu.repository.entity.ExamRepository;
import cn.edu.gzmu.repository.entity.ExamRuleRepository;
import cn.edu.gzmu.service.ExamRuleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


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
    protected ExamRule completeEntity(ExamRule entity) {
        return entity
                .setExam(examRepository.findById(entity.getExamId()).orElse(null));
    }

    @Override
    public List<ExamRule> searchByExamId(Long id) {
        return examRuleRepository.findAllByExamId(id);
    }
}
