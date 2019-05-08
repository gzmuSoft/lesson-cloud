package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.repository.entity.StudentRepository;
import cn.edu.gzmu.service.StudentService;
import org.springframework.stereotype.Service;


/**
 * Student Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
public class StudentServiceImpl extends BaseServiceImpl<StudentRepository, Student, Long>
        implements StudentService {

}
