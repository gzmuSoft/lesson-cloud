package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.Paper;
import cn.edu.gzmu.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Paper Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:05:30
 */
@RepositoryRestResource(path = "/papers")
public interface PaperRepository extends BaseRepository<Paper, Long> {

}