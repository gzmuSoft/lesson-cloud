package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.UserConnection;
import cn.edu.gzmu.repository.entity.UserConnectionRepository;
import cn.edu.gzmu.service.UserConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * UserConnection Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-20 9:18:57
 */
@Service
@RequiredArgsConstructor
public class UserConnectionServiceImpl extends BaseServiceImpl<UserConnectionRepository, UserConnection, Long>
        implements UserConnectionService {

}
