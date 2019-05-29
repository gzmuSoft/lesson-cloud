package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Exam;
import cn.edu.gzmu.repository.entity.ExamRepository;
import cn.edu.gzmu.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * Exam Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class ExamServiceImpl extends BaseServiceImpl<ExamRepository, Exam, Long>
        implements ExamService {

}
