package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.constant.QuestionType;
import cn.edu.gzmu.model.entity.*;
import cn.edu.gzmu.model.exception.ResourceNotFoundException;
import cn.edu.gzmu.repository.entity.*;
import cn.edu.gzmu.service.PaperDetailService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * PaperDetail Service Impl
 *
 * @author echo
 * @author YMS
 * @version 1.0
 * @date 2019-5-7 11:33:57
 * <p>
 * 根据 paperDetail 的 QuestionType 和 QuestionId 获取问题的信息
 * 关联查询出问题的课程，章节，知识点（编程题无此三个属性）
 * @date 2019-8-2
 */
@Service
@RequiredArgsConstructor
public class PaperDetailServiceImpl extends BaseServiceImpl<PaperDetailRepository, PaperDetail, Long>
        implements PaperDetailService {
    private final @NonNull PaperDetailRepository paperDetailRepository;
    private final @NonNull SingleSelRepository singleSelRepository;
    private final @NonNull MultiSelRepository multiSelRepository;
    private final @NonNull JudgementRepository judgementRepository;
    private final @NonNull EssayRepository essayRepository;
    private final @NonNull ProgramRepository programRepository;
    private final @NonNull CourseRepository courseRepository;
    private final @NonNull SectionRepository sectionRepository;
    private final @NonNull KnowledgeRepository knowledgeRepository;

    @Override
    protected PaperDetail completeEntity(PaperDetail paperDetail) {
        if (QuestionType.isSingleSel(paperDetail.getQuestionType())) {
            SingleSel singleSel = singleSelRepository.findById(paperDetail.getQuestionId()).orElseThrow(
                    () -> new ResourceNotFoundException("SingleSel can not be find!"));
            singleSel.setCourse(courseRepository.findById(singleSel.getCourseId()).orElseThrow(
                    () -> new ResourceNotFoundException("Course can not be find!")
            ));
            singleSel.setSection(sectionRepository.findById(singleSel.getSectionId()).orElseThrow(
                    () -> new ResourceNotFoundException("Section can not be find!")
            ));
            singleSel.setKnowledge(knowledgeRepository.findById(singleSel.getKnowledgeId()).orElseThrow(
                    () -> new ResourceNotFoundException("Knowledge can not be find!")
            ));
            return paperDetail.setSingleSel(singleSel);
        } else if (QuestionType.isMultiSel(paperDetail.getQuestionType())) {
            MultiSel multiSel = multiSelRepository.findById(paperDetail.getQuestionId()).orElseThrow(
                    () -> new ResourceNotFoundException("MultiSel can not be find!"));
            multiSel.setCourse(courseRepository.findById(multiSel.getCourseId()).orElseThrow(
                    () -> new ResourceNotFoundException("Course can not be find!")
            ));
            multiSel.setSection(sectionRepository.findById(multiSel.getSectionId()).orElseThrow(
                    () -> new ResourceNotFoundException("Section can not be find!")
            ));
            multiSel.setKnowledge(knowledgeRepository.findById(multiSel.getKnowledgeId()).orElseThrow(
                    () -> new ResourceNotFoundException("Knowledge can not be find!")
            ));
            return paperDetail.setMultiSel(multiSel);
        } else if (QuestionType.isJudgement(paperDetail.getQuestionType())) {
            Judgement judgement = judgementRepository.findById(paperDetail.getQuestionId()).orElseThrow(
                    () -> new ResourceNotFoundException("Judgement can not be find!"));
            judgement.setCourse(courseRepository.findById(judgement.getCourseId()).orElseThrow(
                    () -> new ResourceNotFoundException("Course can not be find!")
            ));
            judgement.setSection(sectionRepository.findById(judgement.getSectionId()).orElseThrow(
                    () -> new ResourceNotFoundException("Section can not be find!")
            ));
            judgement.setKnowledge(knowledgeRepository.findById(judgement.getKnowledgeId()).orElseThrow(
                    () -> new ResourceNotFoundException("Knowledge can not be find!")
            ));
            return paperDetail.setJudgement(judgement);
        } else if (QuestionType.isEssay(paperDetail.getQuestionType())) {
            Essay essay = essayRepository.findById(paperDetail.getQuestionId()).orElseThrow(
                    () -> new ResourceNotFoundException("Essay can not be find!"));
            essay.setCourse(courseRepository.findById(essay.getCourseId()).orElseThrow(
                    () -> new ResourceNotFoundException("Course can not be find!")
            ));
            essay.setSection(sectionRepository.findById(essay.getSectionId()).orElseThrow(
                    () -> new ResourceNotFoundException("Section can not be find!")
            ));
            essay.setKnowledge(knowledgeRepository.findById(essay.getKnowledgeId()).orElseThrow(
                    () -> new ResourceNotFoundException("Knowledge can not be find!")
            ));
            return paperDetail.setEssay(essay);
        } else if (QuestionType.isProgram(paperDetail.getQuestionType())) {
            return paperDetail.setProgram(programRepository.findById(paperDetail.getQuestionId()).orElseThrow(
                    () -> new ResourceNotFoundException("Program can not be find!")
            ));
        }
        return paperDetail;
    }
}
