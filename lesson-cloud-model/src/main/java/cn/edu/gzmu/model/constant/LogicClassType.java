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
    CLASSES(0),
    /**
     * 当前逻辑班级中需要添加新的学生
     */
    STUDENT(1);

    private int value;

    LogicClassType(int i) {
        value = i;
    }

    public boolean match(int type) {
        return value == type;
    }

    public boolean isClass(int type) {
        return type == CLASSES.value;
    }

    public boolean isStudent(int type) {
        return type == STUDENT.value;
    }
}
