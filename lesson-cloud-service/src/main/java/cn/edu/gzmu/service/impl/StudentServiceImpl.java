package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.model.exception.ResourceNotFoundException;
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

    private final @NonNull SysDataRepository sysDataRepository;
    private final @NonNull SysUserRepository sysUserRepository;

    @Override
    public Student completeEntity(Student entity) {
        entity.setSchool(sysDataRepository.getOne(entity.getSchoolId()));
        entity.setCollege(sysDataRepository.getOne(entity.getSchoolId()));
        entity.setDep(sysDataRepository.getOne(entity.getDepId()));
        entity.setSpecialty(sysDataRepository.getOne(entity.getSpecialtyId()));
        entity.setClasses(sysDataRepository.getOne(entity.getClassId()));
        entity.setUser(sysUserRepository.getOne(entity.getUserId()));
        return entity;
    }

}
