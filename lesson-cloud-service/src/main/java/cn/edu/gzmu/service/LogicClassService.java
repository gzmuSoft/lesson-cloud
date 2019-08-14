package cn.edu.gzmu.service;

import cn.edu.gzmu.model.entity.LogicClass;
import cn.edu.gzmu.model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * LogicClass Service
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-20 9:18:57
 *
 * <p>
 *  获取当前登录学生的所有逻辑班级（课程）上课时间表及地点信息，不分页
 *  @author hzl
 *  @date 2019-8-13 15:31</p>
 */
public interface LogicClassService extends BaseService<LogicClass, Long> {

    /**
     * 查询所有逻辑班级上课时间地点信息
     * @param student 逻辑班级
     * @return 逻辑班级信息
     */
    List<LogicClass> findAllCourseTimetableLocation(Student student);

}
