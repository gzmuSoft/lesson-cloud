package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.Knowledge;
import cn.edu.gzmu.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Knowledge Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-20 0:08:37
 */
@RepositoryRestResource
public interface KnowledgeRepository extends BaseRepository<Knowledge, Long> {

}