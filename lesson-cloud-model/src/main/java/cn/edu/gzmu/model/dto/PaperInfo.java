package cn.edu.gzmu.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author BugRui
 * @date 2020/1/20 下午4:41
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PaperInfo implements Serializable {
    /**
     * examId
     */
    private Long examId;
    /**
     * paper Id
     */
    private Long id;
    /**
     * 单选题 info
     */
    private List<QuestionInfo> singleSel;
    /**
     * 多选题info
     */
    private List<QuestionInfo> multiSel;
    /**
     * 判断题info
     */
    private List<QuestionInfo> judgement;
    /**
     * 填空题info
     */
    private List<QuestionInfo> fillBlank;
    /**
     * 简答题info
     */
    private List<QuestionInfo> essay;
    /**
     * 编程题info
     */
    private List<QuestionInfo> program;
}
