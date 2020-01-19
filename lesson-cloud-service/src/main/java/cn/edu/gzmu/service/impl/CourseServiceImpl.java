package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Course;
import cn.edu.gzmu.model.entity.LogicClass;
import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.model.entity.Teacher;
import cn.edu.gzmu.repository.entity.CourseRepository;
import cn.edu.gzmu.repository.entity.LogicClassRepository;
import cn.edu.gzmu.service.CourseService;
import com.google.common.collect.Sets;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    private List<Course> searchByStudent(Student student) {
        // 获取当前学生的物理班级信息
        Long classesId = student.getClassesId();
        // 查找当前学生所在物理班级的逻辑班级
        Set<LogicClass> logicClassesByClassesId = logicClassRepository.findDistinctByClassesId(classesId);
        // 查找当前学生的逻辑班级 ———— 额外添加的情况，如跟班重修
        Set<LogicClass> logicClassesByStudentId = logicClassRepository.findDistinctByStudentId(student.getId());
        // 合并两个 set
        Sets.SetView<LogicClass> logicClasses = Sets.union(logicClassesByClassesId, logicClassesByStudentId);
        // 得到所有的 id
        List<Long> courseIds = logicClasses.stream()
                .map(LogicClass::getCourseId)
                .collect(Collectors.toList());
        // 通过 id 得到课程
        return courseRepository.searchAllByIds(courseIds);
    }

    @Override
    public Page<Course> searchByStudent(Student student, Pageable pageable) {
        List<Course> courses = searchByStudent(student);
        return new PageImpl<>(courses, pageable, courses.size());
    }

    @Override
    public Page<Course> searchByTeacher(Teacher teacher, Pageable pageable) {
        // TODO: 逻辑可能需要优化
        Set<LogicClass> logicClasses = logicClassRepository.findDistinctByTeacherId(teacher.getId());
        List<Long> courseIds = logicClasses.stream()
                .map(LogicClass::getCourseId)
                .collect(Collectors.toList());
        return courseRepository.searchAllByIds(courseIds, pageable);
    }

}
