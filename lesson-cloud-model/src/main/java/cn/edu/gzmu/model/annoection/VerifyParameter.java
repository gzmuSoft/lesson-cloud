package cn.edu.gzmu.model.annoection;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
@Documented
public @interface VerifyParameter {
    /**
     * 验证的参数
     *
     * @return 需要验证的字段
     */
    String[] required() default "";

    /**
     * 验证只能为数字
     *
     * @return 需要验证的字段
     */
    String[] number() default "";

    /**
     * 需要验证大小的字段
     * <p>
     * 必须按照如下格式
     * username|1-5  ： username 的长度范围为 1 - 5
     *
     * @return 需要验证的字段
     */
    String[] size() default "";

    /**
     * 需要验证最大值的字段
     * <p>
     * 必须为数字，格式如下
     * age|5  ：  age 字段最大为 5
     *
     * @return 需要验证的字段
     */
    String[] max() default "";

    /**
     * 需要验证最小值的字段
     * <p>
     * 必须为数字，格式如下
     * age|5  ：  age 字段最小为 5
     *
     * @return 需要验证的字段
     */
    String[] min() default "";

    /**
     * 需要验证范围的字段
     * <p>
     * 必须为数字，格式如下
     * age|1-5  ：  age 字段范围为 1-5
     *
     * @return 需要验证的字段
     */
    String[] range() default "";

    /**
     * 需要相等的字段
     * <p>
     * 必须为数字，格式如下
     * age|1  ：  age 字段必须为 1
     * email|xxx@163.com : email 字段必须为 xxx@163.com
     *
     * @return 需要验证的字段
     */
    String[] equal() default "";

}
