package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.*;
import cn.edu.gzmu.model.exception.ResourceNotFoundException;
import cn.edu.gzmu.repository.entity.*;
import cn.edu.gzmu.service.PaperService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


/**
 * Paper Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class PaperServiceImpl extends BaseServiceImpl<PaperRepository, Paper, Long>
        implements PaperService {
    private final @NonNull ExamRepository examRepository;
    private final @NonNull SingleSelRepository singleSelRepository;
    private final @NonNull MultiSelRepository multiSelRepository;
    private final @NonNull JudgementRepository judgementRepository;
    private final @NonNull EssayRepository essayRepository;
    private final @NonNull ProgramRepository programRepository;

    @Override
    protected Paper completeEntity(Paper entity) {
        entity.setExam(examRepository.findById(entity.getExamId()).orElseThrow(
                () -> new ResourceNotFoundException("Exam can not be find!")
        ));
        ArrayList<SingleSel> singleSels = new ArrayList<>();
        for (String s : entity.getSingleSelIds().split(",")) {
            singleSels.add(singleSelRepository.findById(Long.valueOf(s)).orElseThrow(
                    () -> new ResourceNotFoundException("SingleSel can not be find!")
            ));
        }
        entity.setSingleSel(singleSels);
        ArrayList<MultiSel> multiSels = new ArrayList<>();
        for (String s : entity.getMultiSelIds().split(",")) {
            multiSels.add(multiSelRepository.findById(Long.valueOf(s)).orElseThrow(
                    () -> new ResourceNotFoundException("MultiSel can not be find!")
            ));
        }
        entity.setMultiSel(multiSels);
        ArrayList<Judgement> judgements = new ArrayList<>();
        for (String s : entity.getJudgementIds().split(",")) {
            judgements.add(judgementRepository.findById(Long.valueOf(s)).orElseThrow(
                    () -> new ResourceNotFoundException("Judgement can not be find!")
            ));
        }
        entity.setJudgement(judgements);
        ArrayList<Essay> essays = new ArrayList<>();
        for (String s : entity.getEssayIds().split(",")) {
            essays.add(essayRepository.findById(Long.valueOf(s)).orElseThrow(
                    () -> new ResourceNotFoundException("Essay can not be find!")
            ));
        }
        entity.setEssay(essays);
        ArrayList<Program> programs = new ArrayList<>();
        for (String s : entity.getProgramIds().split(",")) {
            programs.add(programRepository.findById(Long.valueOf(s)).orElseThrow(
                    () -> new ResourceNotFoundException("Program can not be find!")
            ));
        }
        entity.setProgram(programs);
        return entity;
    }

}
