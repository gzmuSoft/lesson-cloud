package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.repository.entity.StudentRepository;
import cn.edu.gzmu.repository.entity.SysDataRepository;
import cn.edu.gzmu.repository.entity.SysUserRepository;
import cn.edu.gzmu.service.StudentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Student Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class StudentServiceImpl extends BaseServiceImpl<StudentRepository, Student, Long>
        implements StudentService {

    private final @NonNull StudentRepository studentRepository;
    private final @NonNull SysDataRepository sysDataRepository;
    private final @NonNull SysUserRepository sysUserRepository;

    @Override
    public Page<Student> searchAll(Pageable pageable) {
        return studentRepository.findAll(pageable).map(student -> {
            // 我真的不喜欢那么多 if
            // 然而三元表达式又难以理解
            // 无奈无奈 (；′⌒`)
            student.setSchool(sysDataRepository.getOne(student.getSchoolId()));
            student.setCollege(sysDataRepository.getOne(student.getSchoolId()));
            student.setDep(sysDataRepository.getOne(student.getDepId()));
            student.setSpecialty(sysDataRepository.getOne(student.getSpecialtyId()));
            student.setClasses(sysDataRepository.getOne(student.getClassId()));
            student.setUser(sysUserRepository.getOne(student.getUserId()));
            return student;
        });
    }
}
