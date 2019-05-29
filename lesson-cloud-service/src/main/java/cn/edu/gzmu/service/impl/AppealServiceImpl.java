package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Appeal;
import cn.edu.gzmu.repository.entity.AppealRepository;
import cn.edu.gzmu.service.AppealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * Appeal Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-20 9:18:57
 */
@Service
@RequiredArgsConstructor
public class AppealServiceImpl extends BaseServiceImpl<AppealRepository, Appeal, Long>
        implements AppealService {

}
