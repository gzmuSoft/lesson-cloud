package cn.edu.gzmu.model.dto;

import cn.edu.gzmu.model.constant.QuestionType;
import cn.edu.gzmu.model.entity.PaperQuestion;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 传输给前端的考试试题
 *
 * @author BugRui
 * @date 2020/1/20 下午4:26
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class QuestionInfo implements Serializable {
    /**
     * question id
     */
    private Long id;
    /**
     * exam id
     */
    private Long examId;
    /**
     * question name
     */
    private String name;
    /**
     * question spell
     */
    private String spell;
    /**
     * 难度系数
     */
    private Integer difficultRate;
    /**
     * question 类型
     */
    private QuestionType questionType;
    /**
     * 分值
     */
    private Float value;
    /**
     * 问题细节
     */
    private QuestionDetail questionDetail;

    public PaperQuestion modelMapperPaperQuestion(Long paperId) {
        PaperQuestion paperQuestion = new PaperQuestion();
        paperQuestion.setQuestionId(id)
                .setPaperId(paperId)
                .setQuestionDetail(JSON.parseObject(
                        JSONObject.toJSONString(questionDetail)));
        return paperQuestion;
    }
}
