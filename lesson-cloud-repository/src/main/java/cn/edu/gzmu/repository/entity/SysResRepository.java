package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.SysRes;
import cn.edu.gzmu.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * SysRes Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-11 17:24:42
 */
@RepositoryRestResource
public interface SysResRepository extends BaseRepository<SysRes, Long> {

}