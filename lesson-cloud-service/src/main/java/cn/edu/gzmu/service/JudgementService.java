package cn.edu.gzmu.service;

import cn.edu.gzmu.model.entity.Judgement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * Judgement Service
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
public interface JudgementService extends BaseService<Judgement, Long> {

    Page<Judgement> searchAll(Pageable pageable);
}
