package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Essay;
import cn.edu.gzmu.repository.entity.EssayRepository;
import cn.edu.gzmu.service.EssayService;
import org.springframework.stereotype.Service;


/**
* Essay Service Impl
*
* @author echo
* @version 1.0
* @date 2019-5-7 11:05:31
*/
@Service
public class EssayServiceImpl extends BaseServiceImpl<EssayRepository, Essay, Long>
        implements EssayService {

}
