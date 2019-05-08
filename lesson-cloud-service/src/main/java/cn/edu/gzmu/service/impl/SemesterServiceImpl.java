package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Semester;
import cn.edu.gzmu.repository.entity.SemesterRepository;
import cn.edu.gzmu.service.SemesterService;
import org.springframework.stereotype.Service;


/**
 * Semester Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
public class SemesterServiceImpl extends BaseServiceImpl<SemesterRepository, Semester, Long>
        implements SemesterService {

}
