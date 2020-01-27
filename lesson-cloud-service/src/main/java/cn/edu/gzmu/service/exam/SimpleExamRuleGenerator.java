package cn.edu.gzmu.service.exam;

import cn.edu.gzmu.model.dto.ExamRuleDetailInfo;
import cn.edu.gzmu.model.dto.QuestionDetail;
import cn.edu.gzmu.model.dto.QuestionInfo;
import cn.edu.gzmu.model.entity.Exam;
import cn.edu.gzmu.model.entity.ExamRule;
import cn.edu.gzmu.model.entity.Knowledge;
import cn.edu.gzmu.model.entity.KnowledgeQuestion;
import cn.edu.gzmu.model.entity.Question;
import cn.edu.gzmu.model.entity.Section;
import cn.edu.gzmu.model.exception.ResourceNotFoundException;
import cn.edu.gzmu.repository.entity.ExamRepository;
import cn.edu.gzmu.repository.entity.KnowledgeQuestionRepository;
import cn.edu.gzmu.repository.entity.KnowledgeRepository;
import cn.edu.gzmu.repository.entity.QuestionRepository;
import cn.edu.gzmu.repository.entity.SectionRepository;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author BugRui
 * @date 2020/1/19 下午11:15
 **/
@Component
@RequiredArgsConstructor
public class SimpleExamRuleGenerator implements ExamRuleGenerator {

    @Data
    @Builder
    @AllArgsConstructor
    @Accessors(chain = true)
    private static class ExamRuleDetail {
        /**
         * 必选题ids
         */
        protected List<Long> requireQuestionIds;
        /**
         * 节 ids
         */
        protected List<Long> sectionIds;
        /**
         * 章 ids
         */
        protected List<Long> passageIds;
        /**
         * 知识点ids
         */
        protected List<Long> knowledgeIds;

        private ExamRuleDetail() {
        }

        public static ExamRuleDetail convert(JSONObject jsonObject) {
            ExamRuleDetailInfo convert = ExamRuleDetailInfo.convert(new ExamRuleDetailInfo(), jsonObject);
            return ExamRuleDetail.builder().requireQuestionIds(convert.getRequireQuestionIds())
                    .passageIds(convert.getPassageIds()).knowledgeIds(convert.getKnowledgeIds())
                    .sectionIds(convert.getSectionIds()).build();

        }
    }

    private final @NonNull QuestionRepository questionRepository;

    private final @NonNull KnowledgeQuestionRepository knowledgeQuestionRepository;

    private final @NonNull SectionRepository sectionRepository;

    private final @NonNull KnowledgeRepository knowledgeRepository;

    private final @NonNull ExamRepository examRepository;


    @Override
    public List<QuestionInfo> generateQuestion(ExamRule examRule) {
        ExamRuleDetail ruleDetail = ExamRuleDetail.convert(examRule.getRuleDetail());
        Set<Long> sectionIds;
        //如果没有exam rule 从课程开始出题
        if (BooleanUtils.and(new Boolean[]{CollectionUtils.isEmpty(ruleDetail.getSectionIds()),
                CollectionUtils.isEmpty(ruleDetail.getPassageIds()), CollectionUtils.isEmpty(ruleDetail.getKnowledgeIds())})) {
            Exam exam = examRepository.findById(examRule.getExamId()).orElseThrow(ResourceNotFoundException::new);
            List<Section> sectionList = sectionRepository.findAllByCourseId(exam.getCourseId());
            sectionIds = sectionList.stream().map(Section::getId).collect(Collectors.toSet());
        } else {
            //查询出符合条件的待选择的selectQuestionIds
            List<Section> sectionList = sectionRepository.findAllByParentIdIn(ruleDetail.getPassageIds());
            sectionIds = sectionList.stream().map(Section::getId).collect(Collectors.toSet());
            sectionIds.addAll(ruleDetail.getSectionIds());
        }
        List<Knowledge> knowledgeList = knowledgeRepository.findAllBySectionIdIn(new ArrayList<>(sectionIds));
        Set<Long> knowledgeIds = knowledgeList.stream().map(Knowledge::getId).collect(Collectors.toSet());
        knowledgeIds.addAll(ruleDetail.getKnowledgeIds());
        List<KnowledgeQuestion> knowledgeQuestionList = knowledgeQuestionRepository.findAllByKnowledgeIdIn(new ArrayList<>(knowledgeIds));
        List<Long> selectQuestionIds = knowledgeQuestionList.stream().map(KnowledgeQuestion::getQuestionId).collect(Collectors.toList());
        List<Question> questionList;
        //简单生成试题 如果没有必选题 使用if里面的步骤
        if (ruleDetail.getRequireQuestionIds().size() == 0) {
            questionList = questionRepository.simpleGenerateQuestion(selectQuestionIds,
                    examRule.getQuestionCount(), examRule.getQuestionType().ordinal(),
                    examRule.getStartDifficultRate(), examRule.getEndDifficultRate());
        } else {
            questionList = questionRepository.simpleGenerateQuestion(selectQuestionIds,
                    examRule.getQuestionCount() - ruleDetail.getRequireQuestionIds().size(),
                    ruleDetail.getRequireQuestionIds(), examRule.getQuestionType().ordinal(),
                    examRule.getStartDifficultRate(), examRule.getEndDifficultRate());
        }
        //TODO 这里可以增加一下判断  由于json存储 id没有强关联  选出的问题数量可能不准确
        if (questionList.size() != examRule.getQuestionCount()) {
            throw new ResourceNotFoundException("生成试卷失败，规则有误");
        }
        return questionList.stream().map(question -> modelMapper(question, examRule)).collect(Collectors.toList());
    }


    private QuestionInfo modelMapper(Question question, ExamRule examRule) {
        QuestionInfo questionInfo = new QuestionInfo();
        QuestionDetail questionDetail = QuestionDetail.convert(new QuestionDetail(), question.getQuestionDetail());
        questionDetail.randomAnswer();
        return questionInfo
                .setId(question.getId())
                .setName(question.getName())
                .setSpell(question.getSpell())
                .setDifficultRate(question.getDifficultRate())
                .setValue(examRule.getEachValue())
                .setQuestionType(question.getType())
                .setQuestionDetail(questionDetail);
    }


}
