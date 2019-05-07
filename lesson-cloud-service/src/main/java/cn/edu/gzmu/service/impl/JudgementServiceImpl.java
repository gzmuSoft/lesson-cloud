package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Judgement;
import cn.edu.gzmu.repository.entity.JudgementRepository;
import cn.edu.gzmu.service.JudgementService;
import org.springframework.stereotype.Service;


/**
* Judgement Service Impl
*
* @author echo
* @version 1.0
* @date 2019-5-7 11:05:31
*/
@Service
public class JudgementServiceImpl extends BaseServiceImpl<JudgementRepository, Judgement, Long>
        implements JudgementService {

}
