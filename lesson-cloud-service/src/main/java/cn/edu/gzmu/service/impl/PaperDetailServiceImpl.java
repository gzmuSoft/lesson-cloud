package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.constant.QuestionType;
import cn.edu.gzmu.model.entity.PaperDetail;
import cn.edu.gzmu.repository.entity.*;
import cn.edu.gzmu.service.PaperDetailService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * PaperDetail Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
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

    @Override
    public Page<PaperDetail> searchAll(Pageable pageable){
        return paperDetailRepository.findAll(pageable).map(paperDetail -> {
            if (QuestionType.isSingleSel(paperDetail.getQuestionType())) {
                paperDetail.setSingleSel(singleSelRepository.getOne(paperDetail.getPaperId()));
            } else if (QuestionType.isMultiSel(paperDetail.getQuestionType())) {
                paperDetail.setMultiSel(multiSelRepository.getOne(paperDetail.getPaperId()));
            } else if (QuestionType.isJudgement(paperDetail.getQuestionType())){
                paperDetail.setJudgement(judgementRepository.getOne(paperDetail.getPaperId()));
            } else if (QuestionType.isEssay(paperDetail.getQuestionType())){
                paperDetail.setEssay(essayRepository.getOne(paperDetail.getPaperId()));
            } else if (QuestionType.isProgram(paperDetail.getQuestionType())){
                paperDetail.setProgram(programRepository.getOne(paperDetail.getPaperId()));
            }
            return paperDetail;
        });
    }
}
