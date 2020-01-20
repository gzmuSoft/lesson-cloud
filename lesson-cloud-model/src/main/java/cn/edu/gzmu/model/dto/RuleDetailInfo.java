package cn.edu.gzmu.model.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
public class RuleDetailInfo {
    protected List<Long> requireQuestionIds;
    protected List<Long> sectionIds;
    protected List<Long> passageIds;

    public static RuleDetailInfo convert(JSONObject jsonObject) {
        RuleDetailInfo ruleDetailInfo = new RuleDetailInfo();
        try {
            List<Long> sectionList = jsonObject.getJSONArray("sectionIds")
                    .toJavaList(Long.class);
            ruleDetailInfo.setSectionIds(sectionList);
            List<Long> passageList = jsonObject.getJSONArray("passageIds")
                    .toJavaList(Long.class);
            ruleDetailInfo.setSectionIds(passageList);
            List<Long> requireQuestionList = jsonObject.getJSONArray("requireQuestionIds")
                    .toJavaList(Long.class);
            ruleDetailInfo.setSectionIds(requireQuestionList);
        } catch (Exception ignored) {
        }
        return ruleDetailInfo;
    }
}
