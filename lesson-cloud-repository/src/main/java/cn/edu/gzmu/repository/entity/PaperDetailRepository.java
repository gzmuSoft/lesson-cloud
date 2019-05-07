package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.PaperDetail;
import cn.edu.gzmu.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * PaperDetail Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:05:31
 */
@RepositoryRestResource(path = "/paperDetails")
public interface PaperDetailRepository extends BaseRepository<PaperDetail, Long> {

}