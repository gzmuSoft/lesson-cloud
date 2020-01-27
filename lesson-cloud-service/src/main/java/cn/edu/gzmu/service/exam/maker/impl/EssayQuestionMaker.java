package cn.edu.gzmu.service.exam.maker.impl;

import cn.edu.gzmu.model.entity.PaperQuestion;
import cn.edu.gzmu.service.exam.maker.QuestionMaker;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author BugRui
 * @date 2020/1/27 下午3:54
 **/
@Component("ESSAY")
public class EssayQuestionMaker implements QuestionMaker {
    @Override
    public BigDecimal make(List<PaperQuestion> paperQuestionList) {
        //TODO 简答题不打分
        return BigDecimal.ZERO;
    }
}
