package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Exam;
import cn.edu.gzmu.repository.entity.CourseRepository;
import cn.edu.gzmu.repository.entity.ExamRepository;
import cn.edu.gzmu.service.ExamService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Exam Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@RequiredArgsConstructor
@Service
public class ExamServiceImpl extends BaseServiceImpl<ExamRepository, Exam, Long>
        implements ExamService {

    private final @NonNull CourseRepository courseRepository;
    private final @NonNull ExamRepository examRepository;

    @Override
    public Page<Exam> searchAll(Pageable pageable) {
        return examRepository.findAll(pageable).map(exam -> {
            if (exam.getCourseId() != null) {
                exam.setCourse(courseRepository.getOne(exam.getCourseId()));
            }
            return exam;
        });
    }

}
