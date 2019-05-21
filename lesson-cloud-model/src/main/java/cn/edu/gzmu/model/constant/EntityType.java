package cn.edu.gzmu.model.constant;

public enum EntityType {
    /**
     * 管理员
     */
    ADMIN(1),
    /**
     * 教师
     */
    TEACHER(2),
    /**
     * 学生
     */
    STUDENT(3);

    private Integer value;

    EntityType(int i) {
        value = i;
    }

    public Integer value() {
        return value;
    }
}
