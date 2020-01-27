package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.constant.QuestionType;
import cn.edu.gzmu.model.dto.ExamInfo;
import cn.edu.gzmu.model.dto.ExamRuleDetailInfo;
import cn.edu.gzmu.model.dto.PaperInfo;
import cn.edu.gzmu.model.dto.QuestionInfo;
import cn.edu.gzmu.model.entity.Course;
import cn.edu.gzmu.model.entity.Exam;
import cn.edu.gzmu.model.entity.ExamRule;
import cn.edu.gzmu.model.entity.Knowledge;
import cn.edu.gzmu.model.entity.LogicClass;
import cn.edu.gzmu.model.entity.Paper;
import cn.edu.gzmu.model.entity.PaperQuestion;
import cn.edu.gzmu.model.entity.Question;
import cn.edu.gzmu.model.entity.Section;
import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.model.exception.BadRequestException;
import cn.edu.gzmu.model.exception.ResourceNotFoundException;
import cn.edu.gzmu.model.exception.ValidateException;
import cn.edu.gzmu.repository.entity.CourseRepository;
import cn.edu.gzmu.repository.entity.ExamRepository;
import cn.edu.gzmu.repository.entity.ExamRuleRepository;
import cn.edu.gzmu.repository.entity.KnowledgeRepository;
import cn.edu.gzmu.repository.entity.LogicClassRepository;
import cn.edu.gzmu.repository.entity.PaperQuestionRepository;
import cn.edu.gzmu.repository.entity.PaperRepository;
import cn.edu.gzmu.repository.entity.QuestionRepository;
import cn.edu.gzmu.repository.entity.SectionRepository;
import cn.edu.gzmu.service.ExamBusinessService;
import cn.edu.gzmu.service.exam.ExamRuleGenerator;
import cn.edu.gzmu.service.exam.maker.QuestionMaker;
import cn.edu.gzmu.service.helper.OauthHelper;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Splitter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author BugRui
 * @date 2020/1/19 下午11:11
 **/
@Service
@RequiredArgsConstructor
public class ExamBusinessServiceImpl implements ExamBusinessService {
    @Qualifier("SimpleExamRuleGenerator")
    private final @NonNull ExamRuleGenerator examRuleGenerator;

    private final @NonNull ExamRepository examRepository;

    private final @NonNull ExamRuleRepository examRuleRepository;

    private final @NonNull CourseRepository courseRepository;

    private final @NonNull LogicClassRepository logicClassRepository;

    private final @NonNull KnowledgeRepository knowledgeRepository;

    private final @NonNull SectionRepository sectionRepository;

    private final @NonNull QuestionRepository questionRepository;

    private final @NonNull PaperRepository paperRepository;

    private final @NonNull PaperQuestionRepository paperQuestionRepository;

    private final @NonNull OauthHelper oauthHelper;

    private final @NonNull Map<String, QuestionMaker> questionMakerMap;

    @Override
    public PaperInfo generatePaper(Long examId) {
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
        return PaperInfo.convert(examId, questionMap.get(QuestionType.SINGLE_SEL), questionMap.get(QuestionType.MULTI_SEL),
                questionMap.get(QuestionType.JUDGEMENT), questionMap.get(QuestionType.FILL_BLANK),
                questionMap.get(QuestionType.ESSAY), questionMap.get(QuestionType.PROGRAM));
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createExam(ExamInfo examInfo) {
        Exam exam = examInfo.getExam();
        //TODO 一堆的验证
        if (exam.getStartTime().isAfter(exam.getEndTime())) {
            throw new ValidateException("考试开始时间不能小于结束时间");
        }
        if (exam.getEndTime().isAfter(exam.getPublicationTime())) {
            throw new ValidateException("成绩公布时间不能小于结束时间");
        }
        Course course = courseRepository.findById(exam.getCourseId()).orElseThrow(() -> new ValidateException("课程不存在"));
        List<Long> sourceLogicClassIds = exam.getLogicClassIds();
        Set<LogicClass> logicClassSet = logicClassRepository.findDistinctByIdIn(sourceLogicClassIds);
        List<Long> logicClassIds = logicClassSet.stream().map(LogicClass::getId).collect(Collectors.toList());
        sourceLogicClassIds.removeAll(logicClassIds);
        if (sourceLogicClassIds.size() != 0) {
            throw new ValidateException(String.format("班级%s不存在", sourceLogicClassIds.toString()));
        }
        exam = examRepository.save(exam);
        ArrayList<ExamRule> examRuleList = new ArrayList<>();
        Exam finalExam = exam;
        examInfo.getRuleList().forEach(examRule -> {
            if (examRule.getStartDifficultRate() > examRule.getEndDifficultRate()) {
                throw new ValidateException("难度设置有误");
            }
            ExamRuleDetailInfo examRuleDetailInfo = ExamRuleDetailInfo.convert(new ExamRuleDetailInfo(), examRule.getRuleDetail());
            List<Long> sourceKnowledgeIds = examRuleDetailInfo.getKnowledgeIds();
            Set<Knowledge> knowledgeSet = knowledgeRepository.findDistinctByIdIn(sourceKnowledgeIds);
            List<Long> knowledgeIds = knowledgeSet.stream().map(Knowledge::getId).collect(Collectors.toList());
            sourceKnowledgeIds.removeAll(knowledgeIds);
            if (sourceKnowledgeIds.size() != 0) {
                throw new ValidateException(String.format("知识点%s不存在", sourceKnowledgeIds.toString()));
            }

            List<Long> sourcePassageIds = examRuleDetailInfo.getPassageIds();
            Set<Section> passageSet = sectionRepository.findDistinctByIdInAndParentId(sourcePassageIds, 0L);
            List<Long> passageIds = passageSet.stream().map(Section::getId).collect(Collectors.toList());
            sourcePassageIds.removeAll(passageIds);
            if (sourcePassageIds.size() != 0) {
                throw new ValidateException(String.format("章%s不存在", sourcePassageIds.toString()));
            }

            List<Long> sourceSectionIds = examRuleDetailInfo.getSectionIds();
            Set<Section> sectionSet = sectionRepository.findDistinctByIdInAndParentIdNot(sourceSectionIds, 0L);
            List<Long> sectionIds = sectionSet.stream().map(Section::getId).collect(Collectors.toList());
            sourceSectionIds.removeAll(sectionIds);
            if (sourceSectionIds.size() != 0) {
                throw new ValidateException(String.format("节%s不存在", sourceSectionIds.toString()));
            }

            List<Long> sourceRequireQuestionIds = examRuleDetailInfo.getRequireQuestionIds();
            Set<Question> questionSet = questionRepository.findDistinctByIdIn(sourceRequireQuestionIds);
            List<Long> requireQuestionIds = questionSet.stream().map(Question::getId).collect(Collectors.toList());
            sourceRequireQuestionIds.removeAll(requireQuestionIds);
            if (sourceRequireQuestionIds.size() != 0) {
                throw new ValidateException(String.format("必选问题%s不存在", sourceRequireQuestionIds.toString()));
            }
            questionSet.forEach(question -> {
                if (BooleanUtils.isFalse(question.getType().match(examRule.getQuestionType()))) {
                    throw new ValidateException(String.format("必选问题%d类型错误", question.getId()));
                }
            });
            examRuleList.add(examRule.setExamId(finalExam.getId()));
        });
        BigDecimal tempTotalScore = examRuleList.stream().map(examRule -> BigDecimal.valueOf(examRule.getQuestionCount() * examRule.getEachValue())).reduce(BigDecimal.ZERO, BigDecimal::add);
        if (tempTotalScore.compareTo(BigDecimal.valueOf(exam.getTotalScore())) != 0) {
            throw new ValidateException("分值不匹配");
        }
        examRuleRepository.saveAll(examRuleList);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public PaperInfo startExam(Long examId) {
        //当前学生
        Student student = oauthHelper.student();
        Exam exam = examRepository.findById(examId).orElseThrow(ResourceNotFoundException::new);
        List<Long> logicClassIds = exam.getLogicClassIds();
        List<LogicClass> logicClassList = logicClassRepository.findAllByStudentInLogicClasses(logicClassIds, student.getClassesId(), student.getId());
        if (CollectionUtils.isEmpty(logicClassList)) {
            throw new BadRequestException("不能参加此考试");
        }
        List<Paper> paperList = paperRepository.findAllByExamIdAndStudentIdOrderByStartTimeDesc(examId, student.getId());
        if (CollectionUtils.isNotEmpty(paperList)) {
            Paper paper = paperList.get(0);
            if (paper.getSubmitTime() == null) {
                return recoveryExam(examId);
            }
        }
        if (exam.getTotalUseTime() != 0 && exam.getTotalUseTime() <= paperList.size()) {
            throw new BadRequestException("考试已达上限");
        }
        PaperInfo paperInfo = generatePaper(examId);
        Paper paper = new Paper();
        paper.setExamId(examId);
        paper.setStudentId(student.getId());
        paper.setStartTime(LocalDateTime.now());
        paper = paperRepository.save(paper);
        List<PaperQuestion> paperQuestionList = paperInfo.createPaperQuestionList(paper.getId());
        paperQuestionRepository.saveAll(paperQuestionList);
        //清除答案
        paperInfo.setId(paper.getId());
        paperInfo.clearAnswer();
        return paperInfo;
    }


    @Override
    public PaperInfo recoveryExam(Long examId) {
        //当前学生
        Student student = oauthHelper.student();
        List<Paper> paperList = paperRepository.findAllByExamIdAndStudentIdOrderByStartTimeDesc(examId, student.getId());
        //恢复一场考试
        Paper paper = paperList.get(0);
        List<PaperQuestion> paperQuestionList = paperQuestionRepository.findAllByPaperId(paper.getId());
        Map<QuestionType, List<PaperQuestion>> paperQuestionMap = paperQuestionList.stream().collect(Collectors.groupingBy(PaperQuestion::getQuestionType));
        PaperInfo paperInfo = PaperInfo.convert(paper.getExamId(), paper.getId(),
                paperQuestionMap.getOrDefault(QuestionType.SINGLE_SEL, Collections.emptyList()), paperQuestionMap.getOrDefault(QuestionType.MULTI_SEL, Collections.emptyList()),
                paperQuestionMap.getOrDefault(QuestionType.JUDGEMENT, Collections.emptyList()), paperQuestionMap.getOrDefault(QuestionType.FILL_BLANK, Collections.emptyList()),
                paperQuestionMap.getOrDefault(QuestionType.ESSAY, Collections.emptyList()), paperQuestionMap.getOrDefault(QuestionType.PROGRAM, Collections.emptyList()));
        paperInfo.clearAnswer();
        return paperInfo;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void stopExam(PaperInfo paperInfo) {
        Exam exam = examRepository.findById(paperInfo.getExamId()).orElseThrow(ResourceNotFoundException::new);
        Paper paper = paperRepository.findById(paperInfo.getId()).orElseThrow(ResourceNotFoundException::new);
        if (ObjectUtils.isNotEmpty(paper.getSubmitTime())) {
            throw new BadRequestException("不可重复提交");
        }
        if (paper.getStartTime().plusHours(exam.getTotalUseTime()).isBefore(LocalDateTime.now())) {
            throw new BadRequestException("交卷时间已过");
        }
        paper.setSubmitTime(LocalDateTime.now());
        List<PaperQuestion> sourcePaperQuestionList = paperInfo.createPaperQuestionList(paper.getId());
        Map<Long, PaperQuestion> sourcePaperQuestionMap =
                sourcePaperQuestionList.stream().collect(Collectors.toMap(PaperQuestion::getQuestionId, paperQuestion -> paperQuestion, (k1, k2) -> k1));
        List<PaperQuestion> paperQuestionList = paperQuestionRepository.findAllByPaperId(paper.getId());
        //合并前端传递过来的paperQuestion
        paperQuestionList.forEach(paperQuestion -> {
            PaperQuestion sourcePaperQuestion = sourcePaperQuestionMap.get(paperQuestion.getQuestionId());
            if (ObjectUtils.isEmpty(sourcePaperQuestion)) {
                throw new BadRequestException("错误的题目提交");
            }
            JSONObject sourcePaperQuestionQuestionDetail = sourcePaperQuestion.getQuestionDetail();
            JSONObject questionDetail = paperQuestion.getQuestionDetail();
            //只合并前端传递的答案
            questionDetail.put("objectiveAnswer", sourcePaperQuestionQuestionDetail.get("objectiveAnswer"));
            questionDetail.put("subjectiveAnswer", sourcePaperQuestionQuestionDetail.get("subjectiveAnswer"));
        });
        Map<QuestionType, List<PaperQuestion>> paperQuestionMap = paperQuestionList.stream().collect(Collectors.groupingBy(PaperQuestion::getQuestionType));
        //计算出总分 请自行补充maker
        BigDecimal totalScore =
                paperQuestionMap.entrySet().stream().map(questionTypeListEntry ->
                        questionMakerMap.get(questionTypeListEntry.getKey().toString()).make(questionTypeListEntry.getValue())).reduce(BigDecimal.ZERO, BigDecimal::add);
        paper.setScore(totalScore.floatValue());
        paperRepository.save(paper);
        paperQuestionRepository.saveAll(paperQuestionList);

    }

}
