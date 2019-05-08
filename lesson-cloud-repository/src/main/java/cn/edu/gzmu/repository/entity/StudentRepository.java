package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Student Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:05:31
 */
@RepositoryRestResource(path = "/students")
public interface StudentRepository extends BaseRepository<Student, Long> {

}