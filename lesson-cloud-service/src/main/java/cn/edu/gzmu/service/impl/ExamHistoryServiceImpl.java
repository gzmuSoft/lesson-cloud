package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.ExamHistory;
import cn.edu.gzmu.repository.entity.*;
import cn.edu.gzmu.service.ExamHistoryService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


/**
 * ExamHistory Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class ExamHistoryServiceImpl extends BaseServiceImpl<ExamHistoryRepository, ExamHistory, Long>
        implements ExamHistoryService {

    private final @NonNull PaperRepository paperRepository;
    private final @NonNull ExamRepository  examRepository;

    @Override
    protected ExamHistory completeEntity(ExamHistory entity) {
       return entity.setExam(examRepository.findById(entity.getExamId()).orElse(null))
            .setPaper(paperRepository.findById(entity.getPaperId()).orElse(null));
    }
}
