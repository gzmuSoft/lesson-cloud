package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.Exam;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;


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
 */
@RepositoryRestResource(path = "/exam")
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
    @Query(value = "SELECT * FROM exam WHERE logic_class_ids in :ids", nativeQuery = true)
    Page<Exam> findAllByLogicClassIds(List<Long> ids, Pageable pageable);

    /**
     * 查询所有已发布课程考试
     *
     * @param pageable 分页
     * @return 结果
     */
    @RestResource(path = "exam", rel = "exam", description = @Description("通过学生所在逻辑班级查询所有资源"))
    @Query(value = "SELECT * FROM exam where is_publish=1", nativeQuery = true)
    Page<Exam> findAllexam(Pageable pageable);


}
