package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.Course;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Course Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-23 17:38:13
 */
@RepositoryRestResource(path = "/course")
public interface CourseRepository extends BaseRepository<Course, Long> {

}