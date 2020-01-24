package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.LogicClass;
import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Set;


/**
 * LogicClass Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-23 17:38:13
 */
@RepositoryRestResource(path = "logicClass")
public interface LogicClassRepository extends BaseRepository<LogicClass, Long> {

    /**
     * 根据 classes id 查询所有符合条件的逻辑班级
     *
     * @param id student id
     * @return 结果
     */
    Set<LogicClass> findDistinctByClassesId(Long id);

    /**
     * 根据 student id 查询所有符合条件的逻辑班级
     *
     * @param id student id
     * @return 结果
     */
    Set<LogicClass> findDistinctByStudentId(Long id);


    /**
     * 根据 teacher id 查询所有符合条件的逻辑班级
     *
     * @param id teacher id
     * @return 结果
     */
    Set<LogicClass> findDistinctByTeacherId(Long id);

    /**
     * 通过ids查找
     *
     * @param ids ids
     * @return 结果
     */
    Set<LogicClass> findDistinctByIdIn(List<Long> ids);

    /**
     * 查找学生和学期id 查出学生重修的logicClass（ 重修）
     *
     * @param type       1
     * @param studentId  学生id
     * @param semesterId 学期id
     * @return 重修的logicClass（ 重修）
     */
    List<LogicClass> findAllByTypeAndStudentIdAndSemesterId(Boolean type, Long studentId, Long semesterId);

}