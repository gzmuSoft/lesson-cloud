package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Exam;
import cn.edu.gzmu.model.entity.LogicClass;
import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.repository.entity.CourseRepository;
import cn.edu.gzmu.repository.entity.ExamRepository;
import cn.edu.gzmu.repository.entity.LogicClassRepository;
import cn.edu.gzmu.service.ExamService;
import com.google.common.collect.Sets;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Exam Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 * @author ljq
 */
@Service
@RequiredArgsConstructor
public class ExamServiceImpl extends BaseServiceImpl<ExamRepository, Exam, Long>
        implements ExamService {
    private final @NonNull ExamRepository examRepository;
    private final @NonNull CourseRepository courseRepository;
    private final @NonNull LogicClassRepository logicClassRepository;

    @Override
    protected Exam completeEntity(Exam entity) {

        return entity
                .setCourse(courseRepository.findById(entity.getCourseId()).orElse(null));
    }

    @Override
    public Page<Exam> searchByClassAndCourse(String courseId, String classIds, Pageable pageable) {
        //获取根据条件查询到的page
        Page<Exam> page = examRepository.findAllByCourseIdAndLogicClassIds(Long.parseLong(courseId), classIds, pageable);
        //获取列表
        List<Exam> content = page.getContent();
        //遍历
        for (Exam element : content) {
            // 对每个数据进行完整性填充
            completeEntity(element);
        }
        return page;
    }

    @Override
    public Page<Exam> searchByStudentPage(Student student, Pageable pageable) {
        //获取当前学生的逻辑班级
        Set<LogicClass> logicClassesByClassesId = logicClassRepository.findDistinctByClassesId(student.getClassesId());
        Set<LogicClass> logicClassesByStudentId = logicClassRepository.findDistinctByStudentId(student.getId());
        Sets.SetView<LogicClass> logicClasses = Sets.union(logicClassesByClassesId, logicClassesByStudentId);
        List<Long> logicClassesIds = logicClasses.stream()
                .map(LogicClass::getId)
                .collect(Collectors.toList());
        //根据当前学生所在的逻辑班级查询当前学生的全部考试信息
        Page<Exam> exam = examRepository.findAllByLogicClassIds(logicClassesIds, pageable);
        List<Exam> content = exam.getContent();
        for (Exam element : content) {
            completeEntity(element);
        }
        return exam;
    }
}
