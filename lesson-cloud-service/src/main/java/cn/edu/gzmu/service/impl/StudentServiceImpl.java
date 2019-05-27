package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.repository.entity.StudentRepository;
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

    @Override
    public Page<Student> searchAll(Pageable pageable) {
        Page<Student> page = studentRepository.findAll(pageable);
        return page;
    }
}
