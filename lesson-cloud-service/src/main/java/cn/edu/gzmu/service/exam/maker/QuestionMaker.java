package cn.edu.gzmu.service.exam.maker;

import cn.edu.gzmu.model.entity.PaperQuestion;

import java.math.BigDecimal;
import java.util.List;

/**
 * 自动阅卷工具
 *
 * @author BugRui
 * @date 2020/1/27 下午3:27
 **/
public interface QuestionMaker {
    /**
     * 阅卷接口
     *
     * @param paperQuestionList 题目
     * @return 成绩
     */
    BigDecimal make(List<PaperQuestion> paperQuestionList);
}
