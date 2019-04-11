package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.Course;
import cn.edu.gzmu.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Course Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-11 17:24:41
 */
@RepositoryRestResource
public interface CourseRepository extends BaseRepository<Course, Long> {

}