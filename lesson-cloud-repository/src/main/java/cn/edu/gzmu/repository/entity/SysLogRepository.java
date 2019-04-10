package cn.edu.gzmu.repository.entity;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * sys_log Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-10 14:11:03
 */
@RepositoryRestResource
public interface SysLogRepository extends cn.edu.gzmu.repository.BaseRepository<cn.edu.gzmu.model.entity.SysLog, Long> {
}