package cn.edu.gzmu.service.exam.maker.impl;

import cn.edu.gzmu.model.dto.QuestionDetail;
import cn.edu.gzmu.model.entity.PaperQuestion;
import cn.edu.gzmu.service.exam.maker.QuestionMaker;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author BugRui
 * @date 2020/1/27 下午3:52
 **/
@Component("MULTI_SEL")
public class MultiSelQuestionMaker implements QuestionMaker {

    @Data
    @Builder
    @AllArgsConstructor
    @Accessors(chain = true)
    private static class MultiSelQuestionDetail {
        private List<Integer> answer;
        private List<Integer> userAnswer;

        private MultiSelQuestionDetail() {
        }

        public static MultiSelQuestionDetail convert(JSONObject jsonObject) {
            QuestionDetail convert = QuestionDetail.convert(new QuestionDetail(), jsonObject);
            return MultiSelQuestionDetail.builder().answer(convert.getAnswer())
                    .userAnswer(convert.getObjectiveAnswer()).build();
        }
    }

    @Override
    public BigDecimal make(List<PaperQuestion> paperQuestionList) {
        return paperQuestionList.stream().map(paperQuestion -> {
            MultiSelQuestionDetail questionDetail = MultiSelQuestionDetail.convert(paperQuestion.getQuestionDetail());
            if (CollectionUtils.isEqualCollection(questionDetail.getAnswer(), questionDetail.getUserAnswer())) {
                paperQuestion.setObtainValue(paperQuestion.getValue());
                return BigDecimal.valueOf(paperQuestion.getValue());
            } else {
                paperQuestion.setObtainValue((float) 0);
                return BigDecimal.ZERO;
            }
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
