package cn.edu.gzmu.service;

import cn.edu.gzmu.model.entity.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * Exam Service
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
public interface ExamService extends BaseService<Exam, Long> {

    /**
     * 查询所有考试分页信息
     *
     * @param pageable 分页
     * @return 分页结果
     */
    Page<Exam> searchAll(Pageable pageable);
}
