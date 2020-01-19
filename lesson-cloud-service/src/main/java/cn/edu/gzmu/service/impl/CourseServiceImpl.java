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
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
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

    @Override
    public Page<Course> searchByNameAndTypeAndSelf(Teacher teacher, String name, String type, Boolean isSelf, Pageable pageable) {
        return courseRepository.findAll((Specification<Course>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate conjunction = criteriaBuilder.conjunction();
            //如果只查询自己管理的班级课程
            if (BooleanUtils.isTrue(isSelf)) {
                CriteriaBuilder.In<Long> inIds = criteriaBuilder.in(root.get("id").as(Long.class));
                Set<LogicClass> logicClassSet = logicClassRepository.findDistinctByTeacherId(teacher.getId());
                //查询出自己管理的逻辑班级 然后用where in 条件判断
                logicClassSet.stream().map(LogicClass::getCourseId).forEach(inIds::value);
                conjunction = criteriaBuilder.and(conjunction, inIds);
            }
            if (StringUtils.isNoneBlank(name)) {
                conjunction = criteriaBuilder.and(conjunction, criteriaBuilder.like(root.get("name").as(String.class), "%" + name + "%"));
            }
            if (StringUtils.isNoneBlank(type)) {
                conjunction = criteriaBuilder.and(conjunction, criteriaBuilder.like(root.get("type").as(String.class), "%" + type + "%"));
            }
            return criteriaQuery.where(conjunction).getRestriction();
        }, pageable);
    }


}
