package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.CourseTimetableLocation;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


/**
 * CourseTimetableLocation Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-23 17:38:13
 */
@RepositoryRestResource(path = "/courseTimetableLocation")
public interface CourseTimetableLocationRepository extends BaseRepository<CourseTimetableLocation, Long> {


    /**
     * 通过logicClassId 查询所有
     * @param logicClassId 逻辑班级id
     * @return List
     */
    @Query(value = "select * from course_timetable_location where logic_class_id =  ? ", nativeQuery = true)
    List<CourseTimetableLocation> findAllByLogicClassId(long logicClassId);
}