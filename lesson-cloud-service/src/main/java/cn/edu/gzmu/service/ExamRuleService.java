package cn.edu.gzmu.service;

import cn.edu.gzmu.model.entity.ExamRule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * ExamRule Service
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
public interface ExamRuleService extends BaseService<ExamRule, Long> {
    /**
     * 查询所有学生分页信息
     *
     * @param pageable 分页
     * @return 分页结果
     */
    Page<ExamRule> searchAll(Pageable pageable);
}
