package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.LogicClass;
import cn.edu.gzmu.repository.entity.LogicClassRepository;
import cn.edu.gzmu.service.LogicClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * LogicClass Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-20 9:18:57
 */
@Service
@RequiredArgsConstructor
public class LogicClassServiceImpl extends BaseServiceImpl<LogicClassRepository, LogicClass, Long>
        implements LogicClassService {

}
