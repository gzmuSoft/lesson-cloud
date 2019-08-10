package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.ExamHistory;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


/**
 * ExamHistory Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-23 17:38:13
 */
@RepositoryRestResource(path = "/examHistory")
public interface ExamHistoryRepository extends BaseRepository<ExamHistory, Long> {

    /**
     * 根据学生 id 分页查询所有考试历史
     *
     * @param studentId 学生id
     * @param pageable  分页
     * @return 结果
     */
    Page<ExamHistory> findAllByStudentId(Long studentId, Pageable pageable);

    /**
     * 根据学生 id 和 考试 id 查询
     *
     * @param examId    考试 id
     * @param studentId 学生 id
     * @return 结果
     */
    Optional<ExamHistory> findFirstByExamIdAndStudentId(Long examId, Long studentId);
}