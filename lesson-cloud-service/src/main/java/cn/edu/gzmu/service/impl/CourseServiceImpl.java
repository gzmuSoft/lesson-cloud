package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Course;
import cn.edu.gzmu.model.entity.LogicClass;
import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.repository.entity.CourseRepository;
import cn.edu.gzmu.repository.entity.LogicClassRepository;
import cn.edu.gzmu.service.CourseService;
import com.google.common.collect.Sets;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Course Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends BaseServiceImpl<CourseRepository, Course, Long>
        implements CourseService {

    private @NonNull LogicClassRepository logicClassRepository;
    private @NonNull CourseRepository courseRepository;

    @Override
    public List<Course> searchByStudent(Student student) {
        Long classesId = student.getClassesId();
        Set<LogicClass> logicClassesByClassesId = logicClassRepository.findDistinctByClassesId(classesId);
        Set<LogicClass> logicClassesByStudentId = logicClassRepository.findDistinctByStudentId(student.getId());
        Sets.SetView<LogicClass> logicClasses = Sets.union(logicClassesByClassesId, logicClassesByStudentId);
        List<Long> courseIds = logicClasses.stream().map(LogicClass::getCourseId).collect(Collectors.toList());
        return courseRepository.searchAllByIds(courseIds);
    }

}
