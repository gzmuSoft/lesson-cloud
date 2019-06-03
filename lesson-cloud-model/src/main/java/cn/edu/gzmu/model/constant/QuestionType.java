package cn.edu.gzmu.model.constant;

/**
 *  试卷题目类型
 *
 * @author YMS
 * @date 2019-5-30
 */
public enum QuestionType {

    /**
     * 单项选择题
     */
    SINGLESEL(0),
    /**
     * 多项选择题
     */
    MULTISEL(1),
    /**
     * 判断题
     */
    JUDGEMENT(2),

//    NotFound(3),

    /**
     * 简答题
     */
    ESSAY(4),
    /**
     * 编程题
     */
    PROGRAM(5);

    private Integer value;

    QuestionType(Integer order){
        value = order;
    }

    public static boolean isSingleSel(Integer id) {
        return SINGLESEL.value.equals(id);
    }

    public static boolean isMultiSel(Integer id) {
        return MULTISEL.value.equals(id);
    }

    public static boolean isJudgement(Integer id) {
        return JUDGEMENT.value.equals(id);
    }

//    public static boolean isNotFound(Integer id) {
//        return NotFound.value.equals(id);
//    }

    public static boolean isEssay(Integer id) {
        return ESSAY.value.equals(id);
    }

    public static boolean isProgram(Integer id) {
        return PROGRAM.value.equals(id);
    }
}
