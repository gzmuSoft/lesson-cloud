package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.constant.LogicClassType;
import cn.edu.gzmu.model.entity.*;
import cn.edu.gzmu.repository.auth.SemesterRepository;
import cn.edu.gzmu.repository.auth.StudentRepository;
import cn.edu.gzmu.repository.auth.SysDataRepository;
import cn.edu.gzmu.repository.auth.TeacherRepository;
import cn.edu.gzmu.repository.entity.CourseRepository;
import cn.edu.gzmu.repository.entity.LogicClassRepository;
import cn.edu.gzmu.service.LogicClassService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static cn.edu.gzmu.service.helper.RestHelper.*;

/**
 * LogicClass Service Impl
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @author Japoul
 * @version 1.0
 * @date 2019-5-20 9:18:57
 * <p>
 * 逻辑班级联查课程实体
 * @date 2019-8-4 23:33:57
 */
@Service
@RequiredArgsConstructor
public class LogicClassServiceImpl extends BaseServiceImpl<LogicClassRepository, LogicClass, Long>
        implements LogicClassService {

    private final @NonNull CourseRepository courseRepository;
    private final @NonNull SysDataRepository sysDataRepository;
    private final @NonNull StudentRepository studentRepository;
    private final @NonNull TeacherRepository teacherRepository;
    private final @NonNull SemesterRepository semesterRepository;

    @Override
    protected LogicClass completeEntity(LogicClass logicClass) {
        logicClass.setSchool(getByIdForEntity(logicClass.getSchoolId(), sysDataRepository::getOnePath, SysData.class));
        logicClass.setCollege(getByIdForEntity(logicClass.getDepId(), sysDataRepository::getOnePath, SysData.class));
        logicClass.setSpecialty(getByIdForEntity(logicClass.getSpecialtyId(), sysDataRepository::getOnePath, SysData.class));
        if (LogicClassType.CLASSES.match(logicClass.getType())) {
            logicClass.setClasses(getByIdForEntity(logicClass.getClassesId(), sysDataRepository::getOnePath, SysData.class));
        } else if (LogicClassType.STUDENT.match(logicClass.getType())) {
            logicClass.setStudent(getByIdForEntity(logicClass.getStudentId(), studentRepository::getOnePath, Student.class));
        }
        logicClass.setTeacher(getByIdForEntity(logicClass.getTeacherId(), teacherRepository::getOnePath, Teacher.class));
        logicClass.setSemester(getByIdForEntity(logicClass.getSemesterId(), semesterRepository::getOnePath, Semester.class));
        return logicClass
                .setCourse(courseRepository.findById(logicClass.getCourseId()).orElse(null));
    }

}
