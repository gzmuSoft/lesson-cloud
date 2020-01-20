package cn.edu.gzmu.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
public class PaperInfo {
    private Long examId;
    private Long id;
    private List<QuestionInfo> singleSel;
    private List<QuestionInfo> multiSel;
    private List<QuestionInfo> judgement;
    private List<QuestionInfo> fillBlank;
    private List<QuestionInfo> essay;
    private List<QuestionInfo> program;
}
