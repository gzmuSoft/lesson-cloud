package cn.edu.gzmu.model.constant;

/**
 * 试卷题目类型
 *
 * @author YMS
 * @date 2019-5-30
 */
public enum QuestionType {

    /**
     * 单项选择题 0
     */
    SINGLE_SEL("SINGLE_SEL"),
    /**
     * 多项选择题 1
     */
    MULTI_SEL("MULTI_SEL"),
    /**
     * 判断题 2
     */
    JUDGEMENT("JUDGEMENT"),
    /**
     * 填空题 3
     */
    FILL_BLANK("FILL_BLANK"),
    /**
     * 简答题 4
     */
    ESSAY("ESSAY"),
    /**
     * 编程题 5
     */
    PROGRAM("PROGRAM");
    private String name;

    QuestionType(String value) {
        name = value;
    }

    /**
     * 类型匹配
     *
     * @param type 要匹配的类型
     * @return 结果
     */
    public Boolean match(QuestionType type) {
        return this.equals(type);
    }

    @Override
    public String toString() {
        return name;
    }
}
