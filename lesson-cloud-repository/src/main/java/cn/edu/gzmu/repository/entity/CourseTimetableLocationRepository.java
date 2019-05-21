package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.CourseTimetableLocation;
import cn.edu.gzmu.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * CourseTimetableLocation Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-20 9:18:57
 */
@RepositoryRestResource(path = "/courseTimetableLocations")
public interface CourseTimetableLocationRepository extends BaseRepository<CourseTimetableLocation, Long> {

}