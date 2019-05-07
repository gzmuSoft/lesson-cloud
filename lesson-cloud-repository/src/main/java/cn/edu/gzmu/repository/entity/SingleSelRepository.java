package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.SingleSel;
import cn.edu.gzmu.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * SingleSel Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:05:31
 */
@RepositoryRestResource(path = "/singleSels")
public interface SingleSelRepository extends BaseRepository<SingleSel, Long> {

}