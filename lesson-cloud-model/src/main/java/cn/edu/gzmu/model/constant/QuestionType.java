package cn.edu.gzmu.model.constant;

/**
 *  试卷题目类型
 *
 * @author YMS
 * @date 2019-5-30
 */
public enum QuestionType {

    /**
     * 单项选择题 0
     */
    SINGLE_SEL,
    /**
     * 多项选择题 1
     */
    MULTI_SEL,
    /**
     * 判断题 2
     */
    JUDGEMENT,
    /**
     * 填空题 3
     */
    FILL_BLANK,
    /**
     * 简答题 4
     */
    ESSAY,
    /**
     * 编程题 5
     */
    PROGRAM;

    /**
     * 类型匹配
     *
     * @param type 要匹配的类型
     * @return 结果
     */
    public Boolean match(QuestionType type) {
        return this.equals(type);
    }

}
