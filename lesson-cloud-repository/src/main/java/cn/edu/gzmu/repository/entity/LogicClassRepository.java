package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.LogicClass;
import cn.edu.gzmu.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * LogicClass Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-20 9:18:57
 */
@RepositoryRestResource(path = "/logicClasses")
public interface LogicClassRepository extends BaseRepository<LogicClass, Long> {

}