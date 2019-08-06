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

import java.util.List;


/**
 * Exam Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class ExamServiceImpl extends BaseServiceImpl<ExamRepository, Exam, Long>
        implements ExamService {
    private final @NonNull ExamRepository examRepository;
    private final @NonNull CourseRepository courseRepository;

    @Override
    protected Exam completeEntity(Exam entity) {
        return entity
                .setCourse(courseRepository.findById(entity.getCourseId()).orElse(null));
    }


    @Override
    public Page<Exam> searchByClassAndCourse(String courseId, String classIds, Pageable pageable) {
        //获取根据条件查询到的page
        Page<Exam> page = examRepository.findAllByCourseIdAndClassesIds(Long.parseLong(courseId), classIds, pageable);
        //获取列表
        List<Exam> content = page.getContent();
        //遍历
        for (Exam element : content) {
            // 对每个数据进行完整性填充
            completeEntity(element);
        }
        return page;
    }
}
