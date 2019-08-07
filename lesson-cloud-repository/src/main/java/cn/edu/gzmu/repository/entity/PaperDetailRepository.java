package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.PaperDetail;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * PaperDetail Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-23 17:38:13
 */
@RepositoryRestResource(path = "/paperDetail")
public interface PaperDetailRepository extends BaseRepository<PaperDetail, Long> {

}