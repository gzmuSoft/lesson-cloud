package cn.edu.gzmu.service;

import cn.edu.gzmu.model.entity.ExamRule;

import java.util.List;


/**
 * ExamRule Service
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
public interface ExamRuleService extends BaseService<ExamRule, Long> {

    /**
     * 根据考试 id 获取组卷规则
     *
     * @param id id
     * @return 规则
     */
    List<ExamRule> searchByExamId(Long id);

}
