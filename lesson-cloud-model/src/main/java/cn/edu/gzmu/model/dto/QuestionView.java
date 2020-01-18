package cn.edu.gzmu.model.dto;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;

/**
 * 题目视图
 *
 * @author Soul
 * @date 2020/1/18 0:19
 */

@Data
@Entity
@Immutable
@NoArgsConstructor
@AllArgsConstructor
public class QuestionView {
    /**
     * 题目编号
     *
     * @date 2020/1/18 0:19
     */
    @Id
    private Long id;

    /**
     * 题目内容
     *
     * @date 2020/1/18 0:19
     */
    private String name;

    /**
     * 难度系数
     *
     * @date 2020/1/18 0:19
     */
    private float difficultRate;

    /**
     * 课程Id
     *
     * @date 2020/1/18 0:19
     */
    private Long courseId;

    /**
     * 章节Id
     *
     * @date 2020/1/18 0:19
     */
    private Long sectionId;

    /**
     * 知识点Id
     *
     * @date 2020/1/18 0:19
     */
    private Long knowledgeId;

    /**
     * 创建者
     *
     * @date 2020/1/18 0:19
     */
    private String createUser;

    /**
     * 类型
     *
     * @date 2020/1/18 0:19
     */
    private String remark;

    /**
     * 是否公开
     *
     * @date 2020/1/18 0:19
     */
    private boolean isPublic;
}
