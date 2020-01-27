package cn.edu.gzmu.service.exam.maker.impl;

import cn.edu.gzmu.model.constant.QuestionType;
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
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author BugRui
 * @date 2020/1/27 下午3:30
 **/
@Component("SINGLE_SEL")
public class SingleSelQuestionMaker implements QuestionMaker {
    @Data
    @Builder
    @AllArgsConstructor
    @Accessors(chain = true)
    private static class SingleSelQuestionDetail {
        private Integer answer;
        private Integer userAnswer;

        private SingleSelQuestionDetail() {
        }

        public static SingleSelQuestionDetail convert(JSONObject jsonObject) {
            QuestionDetail convert = QuestionDetail.convert(new QuestionDetail(), jsonObject);
            Integer userAnswer = -1;
            if (CollectionUtils.isNotEmpty(convert.getObjectiveAnswer())) {
                userAnswer = convert.getObjectiveAnswer().get(0);
            }
            return SingleSelQuestionDetail.builder().answer(convert.getAnswer().get(0))
                    .userAnswer(userAnswer).build();
        }
    }

    @Override
    public BigDecimal make(List<PaperQuestion> paperQuestionList) {
        return paperQuestionList.stream().map(paperQuestion -> {
            SingleSelQuestionDetail questionDetail = SingleSelQuestionDetail.convert(paperQuestion.getQuestionDetail());
            //如果正确 得分
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
