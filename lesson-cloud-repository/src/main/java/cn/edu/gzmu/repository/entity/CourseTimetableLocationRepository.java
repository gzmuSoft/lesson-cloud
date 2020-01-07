package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.CourseTimetableLocation;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Set;


/**
 * CourseTimetableLocation Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-23 17:38:13
 *
 * *<p>
 *  获取当前登录学生的所有逻辑班级（课程）上课时间表及地点信息，不分页
 *  @author hzl
 *  @date 2019-8-13 15:31</p>
 */
@RepositoryRestResource(path = "courseTimetableLocation")
public interface CourseTimetableLocationRepository extends BaseRepository<CourseTimetableLocation, Long> {



    /**
     * 通过logicClassId 查询所有
     * @param logicClassId 逻辑班级id
     * @return List
     */
    List<CourseTimetableLocation> findDistinctByLogicClassId(long logicClassId);
}