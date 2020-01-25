package cn.edu.gzmu.model.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 前端传输给后端的rule规则 后期可增加更多的信息
 *
 * @author BugRui
 * @date 2020/1/20 下午4:59
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ExamRuleDetailInfo implements Serializable {
    /**
     * 必选题ids
     */
    protected List<Long> requireQuestionIds;
    /**
     * 节 ids
     */
    protected List<Long> sectionIds;
    /**
     * 章 ids
     */
    protected List<Long> passageIds;
    /**
     * 知识点ids
     */
    protected List<Long> knowledgeIds;

    public static ExamRuleDetailInfo convert(ExamRuleDetailInfo examRuleDetailInfo, JSONObject jsonObject) {
        examRuleDetailInfo.setKnowledgeIds(getList(jsonObject, "knowledgeIds", Long.class));
        examRuleDetailInfo.setSectionIds(getList(jsonObject, "sectionIds", Long.class));
        examRuleDetailInfo.setRequireQuestionIds(getList(jsonObject, "requireQuestionIds", Long.class));
        examRuleDetailInfo.setPassageIds(getList(jsonObject, "passageIds", Long.class));
        return examRuleDetailInfo;
    }

    private static <T> List<T> getList(JSONObject jsonObject, String key, Class<T> clazz) {
        try {
            return jsonObject.getJSONArray(key).toJavaList(clazz);
        } catch (Exception ignored) {
            return Collections.emptyList();
        }
    }
}
