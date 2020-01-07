package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.Exam;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


/**
 * Exam Repository
 *
 * @author echo
 * @author Japoul
 * @author ljq
 * @author lxy
 * @version 1.0
 * @date 2019-5-23 17:38:13
 * <p>
 * 根据课程id和逻辑班级id列表查询考试信息
 * 根据课程id查询考试信息
 * @date 2019-8-09 15:38:13
 *
 * <p>
 *
 * <p>
 * @author hzl
 * @date 2019-8-13 23:48:10
 * 获取到当前教师未发布的考试信息
 * </p>
 */
@RepositoryRestResource(path = "exam")
public interface ExamRepository extends BaseRepository<Exam, Long> {

    /**
     * 根据多个id分页查询考试信息
     *
     * @param ids      id列表
     * @param pageable 分页
     * @return 结果
     */
    Page<Exam> findAllByIdIsIn(List ids, Pageable pageable);

    /**
     * 根据课程id查询考试列表
     *
     * @param courseId 课程id
     * @return 结果
     */
    List<Exam> findAllByCourseId(Long courseId);

    /**
     * 根据逻辑班级分页查询考试信息
     *
     * @param ids      逻辑班级
     * @param pageable 分页
     * @return 考试信息
     */
    @RestResource(path = "exam", rel = "exam", description = @Description("通过学生所在逻辑班级查询所有资源"))
    @Query(value = "SELECT * FROM exam WHERE logic_class_ids in (:ids) and is_enable = 1", nativeQuery = true)
    Page<Exam> findAllByLogicClassesIn(@Param("ids") List<Long> ids, Pageable pageable);

//    /**
//     * 查询所有已发布课程考试
//     *
//     * @param pageable 分页
//     * @return 结果
//     */
//    @RestResource(path = "exam", rel = "exam", description = @Description("通过学生所在逻辑班级查询所有资源"))
//    @Query(value = "SELECT * FROM exam where is_publish=1", nativeQuery = true)
//    Page<Exam> findAllexam(Pageable pageable);


    /**
     * 找所有的未发布的考试信息
     *
     * @param publish 是否发布
     * @return 考试信息
     */
    List<Exam> findDistinctByIsPublish(boolean publish);

    /**
     * 通过课程id找所有未发布的考试信息
     *
     * @param id 课程id
     * @param publish 是否发布
     * @return 考试信息
     */
    List<Exam> findDistinctByCourseIdAndIsPublish(Long id, boolean publish);

    /**
     * 通过考试信息ids 查找出不包含考试信息id的记录
     *
     * @param examIds 考试信息ids
     * @param logicClassesIds 逻辑班级ids
     * @return 考试信息
     */
    @RestResource(path = "exam", rel = "exam", description = @Description("通过学生所考试历史的考试ids和逻辑班级ids查询 未完成的考试信息"))
    @Query(value = "SELECT * FROM exam WHERE logic_class_ids in (:logicClassesIds) and id not in (:examIds)  and is_enable = 1", nativeQuery = true)
    Page<Exam> findAllByLogicClassesAndNotInExamIds(@Param("examIds") List<Long> examIds, @Param("logicClassesIds") List<Long> logicClassesIds, Pageable pageable);

    /**
     * 通过考试信息ids 查找出包含考试信息的记录
     *
     * @param examIds 考试信息ids
     * @param logicClassesIds 逻辑班级ids
     * @return 考试信息
     */
    @RestResource(path = "exam", rel = "exam", description = @Description("通过学生所考试历史的考试ids和逻辑班级ids查询 完成的考试信息"))
    @Query(value = "SELECT * FROM exam WHERE logic_class_ids in (:logicClassesIds) and id in (:examIds)  and is_enable = 1", nativeQuery = true)
    Page<Exam> findAllByLogicClassesAndInExamIds(@Param("examIds") List<Long> examIds, @Param("logicClassesIds") List<Long> logicClassesIds, Pageable pageable);

    /**
     * 通过考试信息ids 查找出包含考试信息的记录
     *
     * @param logicClassesIds 逻辑班级ids
     * @return 考试信息
     */
    @RestResource(path = "exam", rel = "exam", description = @Description("通过学生所考试历史的考试ids和逻辑班级ids查询 完成的考试信息"))
    @Query(value = "SELECT * FROM exam WHERE logic_class_ids in (:logicClassesIds)  and is_enable = 1", nativeQuery = true)
    Page<Exam> findAllByLogicClasses(@Param("logicClassesIds") List<Long> logicClassesIds, Pageable pageable);
}
