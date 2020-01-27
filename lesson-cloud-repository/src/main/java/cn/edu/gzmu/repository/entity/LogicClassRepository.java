package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.LogicClass;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    /**
     * 根据三个参数 查出该学生是否属于logicClass 如果有 则List<LogicClass>不为空
     *
     * @param ids       logicClass ids
     * @param classesId 实体班级id
     * @param studentId 学生id
     * @return 结果
     */
    @Query(value = "(select logic_class.* from logic_class join logic_class retake on retake.type=1 and retake.student_id=:studentId and retake.classes_id=logic_class.id  and retake.is_enable=1 where logic_class.id in (:ids) and logic_class.is_enable=1 ) union all(select logic_class.* from logic_class where logic_class.id in (:ids) and logic_class.is_enable=1 and logic_class.classes_id=:classesId )", nativeQuery = true)
    List<LogicClass> findAllByStudentInLogicClasses(@Param("ids") List<Long> ids, @Param("classesId") Long classesId, @Param("studentId") Long studentId);

    /**
     * 根据三个参数 查找该学生是否属于logicClass 如果属于 返回逻辑班级
     *
     * @param id        logicClass id
     * @param classesId 班级id
     * @param studentId 学生id
     * @return 结果
     */
    @Query(value = "(select logic_class.* from logic_class join logic_class retake on retake.type=1 and retake.student_id=:studentId and retake.classes_id=logic_class.id  and retake.is_enable=1 where logic_class.id = :ids and logic_class.is_enable=1 ) union all(select logic_class.* from logic_class where logic_class.id = :id and logic_class.is_enable=1 and logic_class.classes_id=:classesId )", nativeQuery = true)
    LogicClass findByStudentInLogicClass(@Param("id") Long id, @Param("classesId") Long classesId, @Param("studentId") Long studentId);
}