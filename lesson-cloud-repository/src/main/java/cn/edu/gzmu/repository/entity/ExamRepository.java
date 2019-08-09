package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.Exam;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Exam Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-23 17:38:13
 *
 * @author Japoul
 * @date 2019-8-06 22:38:13
 */
@RepositoryRestResource(path = "/exam")
public interface ExamRepository extends BaseRepository<Exam, Long> {

    /**
     * 据班级列表和课程信息分页查询考试信息
     *
     * @param courseId 课程
     * @param classIds 班级列表
     * @param pageable 分页
     * @return 结果
     */
    Page<Exam> findAllByCourseIdAndLogicClassIds(Long courseId, String classIds, Pageable pageable);

}
