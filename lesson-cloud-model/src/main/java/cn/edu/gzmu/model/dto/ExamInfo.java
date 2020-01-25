package cn.edu.gzmu.model.dto;

import cn.edu.gzmu.model.entity.Exam;
import cn.edu.gzmu.model.entity.ExamRule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author BugRui
 * @date 2020/1/21 下午1:50
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ExamInfo implements Serializable {
    /**
     * 具体考试规则
     */
    @javax.validation.constraints.NotEmpty(message = "组卷规则list不能为空")
    private List<ExamRule> ruleList;
    /**
     * 考试信息
     */
    private Exam exam;
}
