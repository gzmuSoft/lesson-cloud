package cn.edu.gzmu.model.constant;

/**
 * 逻辑班级类型
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2019/8/4 下午10:40
 */
public enum LogicClassType {
    /**
     * 当前逻辑班级为正常班级
     */
    CLASSES(false),
    /**
     * 当前逻辑班级中需要添加新的学生
     */
    STUDENT(true);

    private Boolean value;

    LogicClassType(Boolean b) {
        value = b;
    }

    public boolean match(Boolean type) {
        return value ? type : !type;
    }

    public boolean isClass(Boolean type) {
        return !type;
    }

    public boolean isStudent(Boolean type) {
        return type;
    }
}
