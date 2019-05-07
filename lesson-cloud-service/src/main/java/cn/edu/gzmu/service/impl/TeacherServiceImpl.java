package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Teacher;
import cn.edu.gzmu.repository.entity.TeacherRepository;
import cn.edu.gzmu.service.TeacherService;
import org.springframework.stereotype.Service;


/**
* Teacher Service Impl
*
* @author echo
* @version 1.0
* @date 2019-5-7 11:05:31
*/
@Service
public class TeacherServiceImpl extends BaseServiceImpl<TeacherRepository, Teacher, Long>
        implements TeacherService {

}
