package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.Exam;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


/**
 * Exam Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-23 17:38:13
 *
 * 根据课程id和逻辑班级id列表查询考试信息
 * 根据课程id查询考试信息
 * @author Japoul
 * @date 2019-8-09 15:38:13
 */
@RepositoryRestResource(path = "/exam")
public interface ExamRepository extends BaseRepository<Exam, Long> {

    /**
     * 根据多个id分页查询考试信息
     *
     * @param ids id列表
     * @param pageable 分页
     * @return 结果
     */
    Page<Exam> findAllByIdIsIn(List ids, Pageable pageable);

    /**
     * 根据课程id查询考试列表
     *
     * @param courseId
     * @return
     */
    List<Exam> findAllByCourseId(Long courseId);

}
