package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Exam;
import cn.edu.gzmu.model.entity.ExamRule;
import cn.edu.gzmu.model.entity.Paper;
import cn.edu.gzmu.model.exception.ResourceException;
import cn.edu.gzmu.model.exception.ResourceNotFoundException;
import cn.edu.gzmu.repository.entity.ExamRepository;
import cn.edu.gzmu.repository.entity.ExamRuleRepository;
import cn.edu.gzmu.service.ExamGenerateService;
import cn.edu.gzmu.service.exam.ExamRuleGenerator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author BugRui
 * @date 2020/1/19 下午11:11
 **/
@Service
@RequiredArgsConstructor
public class ExamGenerateServiceImpl implements ExamGenerateService {
    @Qualifier("SimpleExamRuleGenerator")
    private final @NonNull ExamRuleGenerator examRuleGenerator;

    private final @NonNull ExamRepository examRepository;

    private final @NonNull ExamRuleRepository examRuleRepository;

    @Override
    public Paper generatePaper(Long examId) {
        Exam exam = examRepository.findById(examId).orElseThrow(ResourceNotFoundException::new);
        List<ExamRule> examRuleList = examRuleRepository.findAllByExamId(exam.getId());
        examRuleList.stream().
        return null;
    }
}
