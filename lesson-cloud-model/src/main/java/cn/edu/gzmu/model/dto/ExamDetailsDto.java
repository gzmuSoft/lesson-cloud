package cn.edu.gzmu.model.dto;

import cn.edu.gzmu.model.entity.Exam;
import cn.edu.gzmu.model.entity.ExamHistory;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 考试详情 dto  请自行完善
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2019/8/9 上午10:51
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamDetailsDto implements Serializable {
    /**
     * 考试信息
     */
    private Exam exam;
    /**
     * 如果参加了考试，此处不为 null
     */
    private ExamHistory examHistory;
    /**
     * 总分
     */
    private Double allScore;
    /**
     * 题目数量
     */
    private Integer count;
}
