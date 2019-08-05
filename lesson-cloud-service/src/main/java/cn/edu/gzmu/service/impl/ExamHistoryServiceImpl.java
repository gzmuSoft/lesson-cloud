package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.ExamHistory;
import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.repository.entity.*;
import cn.edu.gzmu.service.ExamHistoryService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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
    private final @NonNull ExamRepository examRepository;
    private final @NonNull ExamHistoryRepository examHistoryRepository;


    @Override
    protected ExamHistory completeEntity(ExamHistory entity) {
        return entity
                .setExam(examRepository.findById(entity.getExamId()).orElse(null))
                .setPaper(paperRepository.findById(entity.getPaperId()).orElse(null));
    }

    @Override
    public Page<ExamHistory> searchByStudentPage(Student student, Pageable pageable) {
        // 先条件查询获取当权学生的所有考试历史信息
        Page<ExamHistory> examHistories = examHistoryRepository.findAllByStudentId(student.getId(), pageable);
        // 获取到分页信息中的数据
        List<ExamHistory> content = examHistories.getContent();
        // 对数据进行遍历
        for (ExamHistory element : content) {
            // 对每个数据进行完整性填充
            completeEntity(element);
        }
        return examHistories;
    }
}
