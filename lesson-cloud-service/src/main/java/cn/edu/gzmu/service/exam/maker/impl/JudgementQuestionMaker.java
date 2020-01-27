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
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author BugRui
 * @date 2020/1/27 下午3:53
 **/
@Component("JUDGEMENT")
public class JudgementQuestionMaker implements QuestionMaker {

    @Data
    @Builder
    @AllArgsConstructor
    @Accessors(chain = true)
    private static class JudgementQuestionDetail {
        private Integer answer;
        private Integer userAnswer;

        private JudgementQuestionDetail() {
        }

        public static JudgementQuestionDetail convert(JSONObject jsonObject) {
            QuestionDetail convert = QuestionDetail.convert(new QuestionDetail(), jsonObject);
            Integer userAnswer = -1;
            if (CollectionUtils.isNotEmpty(convert.getObjectiveAnswer())) {
                userAnswer = convert.getObjectiveAnswer().get(0);
            }
            return JudgementQuestionDetail.builder().answer(convert.getAnswer().get(0))
                    .userAnswer(userAnswer).build();
        }
    }

    @Override
    public BigDecimal make(List<PaperQuestion> paperQuestionList) {
        return paperQuestionList.stream().map(paperQuestion -> {
            JudgementQuestionDetail questionDetail = JudgementQuestionDetail.convert(paperQuestion.getQuestionDetail());
            if (questionDetail.getAnswer().equals(questionDetail.getUserAnswer())) {
                paperQuestion.setObtainValue(paperQuestion.getValue());
                return BigDecimal.valueOf(paperQuestion.getValue());
            } else {
                paperQuestion.setObtainValue((float) 0);
                return BigDecimal.ZERO;
            }
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
