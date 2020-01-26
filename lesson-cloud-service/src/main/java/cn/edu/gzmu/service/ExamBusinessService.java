package cn.edu.gzmu.service;

import cn.edu.gzmu.model.dto.ExamInfo;
import cn.edu.gzmu.model.dto.PaperInfo;
import cn.edu.gzmu.model.entity.Paper;

/**
 * 生成试卷的Service
 *
 * @author BugRui
 * @date 2020/1/19 下午11:09
 **/
public interface ExamBusinessService {
    /**
     * 根据组卷规则 生成一个Paper
     *
     * @param examId 考试id
     * @return paper
     */
    PaperInfo generatePaper(Long examId);

    /**
     * 创建一场考试
     *
     * @param examInfo exam dto
     */
    void createExam(ExamInfo examInfo);
}
