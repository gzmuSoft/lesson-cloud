package cn.edu.gzmu.model.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 目前只定义选择题和填空题的option和answer
 *
 * @author BugRui
 * @date 2020/1/25 下午10:55
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class QuestionDetail {
    /**
     * 问题选项
     */
    List<String> option = Collections.emptyList();
    /**
     * 问题答案
     */
    List<Integer> answer = Collections.emptyList();


    /**
     * 客观题用户答案
     */
    List<Integer> objectiveAnswer = Collections.emptyList();
    /**
     * 主观题用户答案
     */
    String subjectiveAnswer = "";

    /**
     * 在传输给学生的时候清空答案
     */
    public void clearAnswer() {
        this.answer = Collections.emptyList();
    }

    /**
     * 打乱选项和答案
     */
    public void randomAnswer() {
        ArrayList<String> successOption = new ArrayList<>();
        this.answer.forEach(optionId -> {
            successOption.add(this.option.get(optionId));
        });
        Collections.shuffle(this.option);
        this.answer.clear();
        successOption.forEach(successAnswer -> this.answer.add(this.option.indexOf(successAnswer)));
    }

    public static QuestionDetail convert(QuestionDetail questionDetail, JSONObject jsonObject) {
        questionDetail.setOption(getList(jsonObject, "option", String.class));
        questionDetail.setAnswer(getList(jsonObject, "answer", Integer.class));
        questionDetail.setObjectiveAnswer(getList(jsonObject, "objectiveAnswer", Integer.class));
        questionDetail.setSubjectiveAnswer(getObjectOrDefault(jsonObject, "subjectiveAnswer", String.class, ""));
        return questionDetail;
    }


    private static <T> List<T> getList(JSONObject jsonObject, String key, Class<T> clazz) {
        try {
            return jsonObject.getJSONArray(key).toJavaList(clazz);
        } catch (Exception ignored) {
            return Collections.emptyList();
        }
    }

    private static <T> T getObjectOrDefault(JSONObject jsonObject, String key, Class<T> clazz, Object o) {
        try {
            return jsonObject.getJSONObject(key).toJavaObject(clazz);
        } catch (Exception ignored) {
            return (T) o;
        }
    }
}
