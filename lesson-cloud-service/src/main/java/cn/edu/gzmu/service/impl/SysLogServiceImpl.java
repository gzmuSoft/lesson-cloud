package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.SysLog;
import cn.edu.gzmu.repository.entity.SysLogRepository;
import cn.edu.gzmu.service.SysLogService;
import org.springframework.stereotype.Service;


/**
* SysLog Service Impl
*
* @author echo
* @version 1.0
* @date 2019-5-7 11:05:31
*/
@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLogRepository, SysLog, Long>
        implements SysLogService {

}
