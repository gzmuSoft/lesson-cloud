package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.constant.QuestionType;
import cn.edu.gzmu.model.dto.PaperInfo;
import cn.edu.gzmu.model.dto.QuestionInfo;
import cn.edu.gzmu.model.entity.Exam;
import cn.edu.gzmu.model.entity.ExamRule;
import cn.edu.gzmu.model.entity.Paper;
import cn.edu.gzmu.model.entity.Question;
import cn.edu.gzmu.model.exception.ResourceException;
import cn.edu.gzmu.model.exception.ResourceNotFoundException;
import cn.edu.gzmu.repository.entity.ExamRepository;
import cn.edu.gzmu.repository.entity.ExamRuleRepository;
import cn.edu.gzmu.repository.entity.SelOptionsRepository;
import cn.edu.gzmu.service.ExamGenerateService;
import cn.edu.gzmu.service.exam.ExamRuleGenerator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
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
    public PaperInfo generatePaper(Long examId) {
        Paper paper = new Paper();
        Exam exam = examRepository.findById(examId).orElseThrow(ResourceNotFoundException::new);
        List<ExamRule> examRuleList = examRuleRepository.findAllByExamId(exam.getId());
        HashMap<QuestionType, List<QuestionInfo>> questionMap = new HashMap<>();
        questionMap.put(QuestionType.SINGLE_SEL, new ArrayList<>());
        questionMap.put(QuestionType.MULTI_SEL, new ArrayList<>());
        questionMap.put(QuestionType.JUDGEMENT, new ArrayList<>());
        questionMap.put(QuestionType.FILL_BLANK, new ArrayList<>());
        questionMap.put(QuestionType.ESSAY, new ArrayList<>());
        questionMap.put(QuestionType.PROGRAM, new ArrayList<>());
        examRuleList.forEach(examRule -> {
            questionMap.get(examRule.getQuestionType()).addAll(examRuleGenerator.generateQuestion(examRule));
        });
        return modelMapper(questionMap, exam);
    }

    private PaperInfo modelMapper(HashMap<QuestionType, List<QuestionInfo>> questionMap, Exam exam) {
        PaperInfo paperInfo = new PaperInfo();
        return paperInfo.setExamId(exam.getId())
                .setSingleSel(questionMap.get(QuestionType.SINGLE_SEL))
                .setMultiSel(questionMap.get(QuestionType.MULTI_SEL))
                .setJudgement(questionMap.get(QuestionType.JUDGEMENT))
                .setFillBlank(questionMap.get(QuestionType.FILL_BLANK))
                .setEssay(questionMap.get(QuestionType.ESSAY))
                .setProgram(questionMap.get(QuestionType.PROGRAM));
    }
}
