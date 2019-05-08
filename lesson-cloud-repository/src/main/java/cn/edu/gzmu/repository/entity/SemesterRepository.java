package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.Semester;
import cn.edu.gzmu.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Semester Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:05:31
 */
@RepositoryRestResource(path = "/semesters")
public interface SemesterRepository extends BaseRepository<Semester, Long> {

}