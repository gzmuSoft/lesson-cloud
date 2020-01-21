package cn.edu.gzmu.model.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
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

    public static ExamRuleDetailInfo convert(JSONObject jsonObject) {
        ExamRuleDetailInfo examRuleDetailInfo = new ExamRuleDetailInfo();
        try {
            List<Long> knowledgeList = jsonObject.getJSONArray("knowledgeIds")
                    .toJavaList(Long.class);
            examRuleDetailInfo.setKnowledgeIds(knowledgeList);
            List<Long> sectionList = jsonObject.getJSONArray("sectionIds")
                    .toJavaList(Long.class);
            examRuleDetailInfo.setSectionIds(sectionList);
            List<Long> passageList = jsonObject.getJSONArray("passageIds")
                    .toJavaList(Long.class);
            examRuleDetailInfo.setSectionIds(passageList);
            List<Long> requireQuestionList = jsonObject.getJSONArray("requireQuestionIds")
                    .toJavaList(Long.class);
            examRuleDetailInfo.setSectionIds(requireQuestionList);
        } catch (Exception ignored) {
        }
        return examRuleDetailInfo;
    }
}
