package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.BaseEntity;
import cn.edu.gzmu.model.entity.*;
import cn.edu.gzmu.repository.BaseRepository;
import cn.edu.gzmu.repository.entity.*;
import cn.edu.gzmu.service.PaperService;
import com.google.common.base.Splitter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
        entity.setExam(examRepository.findById(entity.getExamId()).orElse(null));
        entity.setSingleSel(listEntity(entity.getSingleSelIds(), singleSelRepository));
        entity.setMultiSel(listEntity(entity.getMultiSelIds(), multiSelRepository));
        entity.setJudgement(listEntity(entity.getJudgementIds(), judgementRepository));
        entity.setEssay(listEntity(entity.getEssayIds(), essayRepository));
        entity.setProgram(listEntity(entity.getProgramIds(), programRepository));
        return entity;
    }

    private <T extends BaseEntity> List<T> listEntity(
            String ids, BaseRepository<T, Long> baseRepository) {
        if (Objects.isNull(ids)) {
            return null;
        }
        ArrayList<T> entity = new ArrayList<>();
        Splitter.on(",").trimResults().split(ids)
                .forEach(id -> entity.add(
                        baseRepository.findById(Long.valueOf(id)).orElse(null)
                ));
        return entity;
    }

}
