package cn.edu.gzmu.service;

import cn.edu.gzmu.model.dto.ExamInfo;
import cn.edu.gzmu.model.dto.PaperInfo;
import cn.edu.gzmu.model.entity.Paper;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 开始一场考试
     *
     * @param examId 考试id
     * @return 结果
     */
    PaperInfo startExam(Long examId);

    /**
     * 恢复一场考试
     *
     * @param examId 考试id
     * @return paperInfo
     */
    PaperInfo recoveryExam(Long examId);

    /**
     * 结束一场考试
     *
     * @param paperInfo 试卷详情
     */
    void stopExam(PaperInfo paperInfo);
}
