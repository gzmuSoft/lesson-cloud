package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.constant.LogicClassType;
import cn.edu.gzmu.model.entity.*;
import cn.edu.gzmu.repository.auth.SemesterRepository;
import cn.edu.gzmu.repository.auth.StudentRepository;
import cn.edu.gzmu.repository.auth.SysDataRepository;
import cn.edu.gzmu.repository.auth.TeacherRepository;
import cn.edu.gzmu.repository.entity.CourseRepository;
import cn.edu.gzmu.repository.entity.CourseTimetableLocationRepository;
import cn.edu.gzmu.repository.entity.LogicClassRepository;
import cn.edu.gzmu.service.LogicClassService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * LogicClass Service Impl
 *
 *
 * @author Japoul
 * @author hzl
 * @version 1.0
 * @date 2019-5-20 9:18:57
 * <p>
 * 逻辑班级联查课程实体
 * @date 2019-8-4 23:33:57
 *
 * <p>
 * 获取当前登录学生的所有逻辑班级（课程）上课时间表及地点信息，不分页
 * @date 2019-8-13 15:31</p>
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
    private final @NonNull LogicClassRepository logicClassRepository;
    private final @NonNull CourseTimetableLocationRepository courseTimetableLocationRepository;

    @Override
    protected LogicClass completeEntity(LogicClass logicClass) {
        logicClass.setSchool(sysDataRepository.findById(logicClass.getSchoolId()));
        logicClass.setCollege(sysDataRepository.findById(logicClass.getDepId()));
        logicClass.setSpecialty(sysDataRepository.findById(logicClass.getSpecialtyId()));
        if (LogicClassType.CLASSES.match(logicClass.getType())) {
            logicClass.setClasses(sysDataRepository.findById(logicClass.getClassesId()));
        } else if (LogicClassType.STUDENT.match(logicClass.getType())) {
            logicClass.setStudent(studentRepository.findById(logicClass.getSchoolId()));
        }
        logicClass.setTeacher(teacherRepository.findById(logicClass.getTeacherId()));
        logicClass.setSemester(semesterRepository.findById(logicClass.getSemesterId()));
        return logicClass
                .setCourse(courseRepository.findById(logicClass.getCourseId()).orElse(null));
    }


    @Override
    public List<LogicClass> findAllCourseTimetableLocation(Student student) {
        List<LogicClass> logicClass = new ArrayList<>();
        //通过班级获取逻辑班级
        logicClass.addAll(logicClassRepository.findDistinctByClassesId(student.getClassesId()));
        //其他情况，重修的
        logicClass.addAll(logicClassRepository.findDistinctByStudentId(student.getId()));
        //根据逻辑班级id查询到上课时间地点
        for (LogicClass aClass : logicClass) {
            aClass.setCourseTimetableLocationList(courseTimetableLocationRepository.findDistinctByLogicClassId(aClass.getId()));
        }
        return logicClass;
    }
}
