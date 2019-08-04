package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.LogicClass;
import cn.edu.gzmu.model.exception.ResourceNotFoundException;
import cn.edu.gzmu.repository.entity.CourseRepository;
import cn.edu.gzmu.repository.entity.LogicClassRepository;
import cn.edu.gzmu.service.LogicClassService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * LogicClass Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-20 9:18:57
 *
 * 逻辑班级联查课程实体
 * @author Japoul
 * @date 2019-8-4 23:33:57
 */
@Service
@RequiredArgsConstructor
public class LogicClassServiceImpl extends BaseServiceImpl<LogicClassRepository, LogicClass, Long>
        implements LogicClassService {

    private final @NonNull CourseRepository courseRepository;

    @Override
    protected LogicClass completeEntity(LogicClass entity) {
        courseRepository.findById(entity.getCourseId());
        return entity.setCourse(
                courseRepository.findById(entity.getCourseId()).orElse(null));
    }

}
