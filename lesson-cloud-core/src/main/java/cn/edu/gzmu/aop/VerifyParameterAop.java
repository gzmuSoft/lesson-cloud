package cn.edu.gzmu.aop;

import cn.edu.gzmu.model.annoection.VerifyParameter;
import cn.edu.gzmu.model.function.VerifyParameterPredicate;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.validation.ValidationException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author echo
 * @version 1.0
 * @date 19-5-22 14:54
 */
@Aspect
@Component
public class VerifyParameterAop {
    private static final String MARK = "|";

    private JSONObject param;

    /**
     * 切点
     */
    @Pointcut("@annotation(cn.edu.gzmu.model.annoection.VerifyParameter)")
    public void verifyParameter() {
    }

    /**
     * verify 验证
     *
     * @param joinPoint 切点
     * @throws NoSuchMethodException 异常
     */
    @Before("verifyParameter()")
    public void verify(JoinPoint joinPoint) throws NoSuchMethodException {
        Object target = joinPoint.getTarget();
        Method method = target.getClass().getMethod(joinPoint.getSignature().getName(), JSONObject.class);
        param = (JSONObject) joinPoint.getArgs()[0];
        VerifyParameter annotation = method.getAnnotation(VerifyParameter.class);
        check(annotation.required(), (expression, value) -> Objects.nonNull(value), () -> " 为必填项！");
        check(annotation.range(), (expression, value) -> value instanceof Long
                && ((Long) value > getConditionMin(expression)
                && (Long) value < getConditionMax(expression)), () -> " 不存在或不在指定大小范围内！");
        check(annotation.size(), (expression, value) -> Objects.nonNull(value)
                        && value.toString().length() < getConditionMax(expression)
                        && value.toString().length() > getConditionMin(expression),
                () -> " 不存在或长度不合法！");
        check(annotation.max(), (expression, value) -> Objects.nonNull(value)
                        && Long.parseLong(value.toString()) < getMax(expression),
                () -> " 不存在或数值过大！");
        check(annotation.min(), (expression, value) -> Objects.nonNull(value)
                        && Long.parseLong(value.toString()) < getMin(expression),
                () -> " 不存在或数值过小！");
        check(annotation.number(), (expression, value) -> Objects.nonNull(value)
                        && StringUtils.isNumeric(value.toString()),
                () -> " 不存在或不为数字类型！");
        check(annotation.equal(), (expression, value) -> Objects.nonNull(value)
                        && value.toString().equals(getCondition(expression)),
                () -> " 的值不合法！");
    }

    /**
     * 对其参数进行检查
     *
     * @param expressions 检查的表达式
     * @param condition   条件
     * @param handle      异常抛出信息
     */
    private void check(String[] expressions, VerifyParameterPredicate<String, Object> condition,
                       Supplier<String> handle) {
        for (String expression : expressions) {
            if (StringUtils.isEmpty(expression)) {
                continue;
            }
            String field = getField(expression);
            Object value = getValue(field);
            if (!condition.test(expression, value)) {
                throw new ValidationException(field + handle.get());
            }
        }
    }

    /**
     * 获取表达式中的字段
     *
     * @param expression 表达式
     * @return 字段
     */
    private String getField(String expression) {
        return expression.contains(MARK)
                ? StringUtils.substringBefore(expression, MARK)
                : expression;
    }

    /**
     * 获取去表达式中的条件
     *
     * @param expression 表达式
     * @return 条件
     */
    private String getCondition(String expression) {
        return expression.contains(MARK)
                ? StringUtils.substringAfter(expression, MARK)
                : null;
    }

    /**
     * 获取字段对应的参数中的值
     *
     * @param field 字段
     * @return 值
     */
    private Object getValue(String field) {
        List<String> keys = Stream.of(field.split("\\.")).collect(Collectors.toList());
        String target = keys.remove(keys.size() - 1);
        JSONObject param = this.param;
        for (String key : keys) {
            param = param.getJSONObject(key);
            Assert.notNull(param, String.format("%s 为必填项！", key));
        }
        return param.get(target);
    }

    /**
     * 获取表达式中最大的长度，为 Integer 类型
     *
     * @param expression 表达式
     * @return 结果
     */
    private Integer getConditionMax(String expression) {
        String after = StringUtils.substringAfter(getCondition(expression), "-");
        return StringUtils.isNumeric(after) ? Integer.parseInt(after) : 0;
    }

    /**
     * 获取表达式中最小的长度，为 Integer 类型
     *
     * @param expression 表达式
     * @return 结果
     */
    private Integer getConditionMin(String expression) {
        String before = StringUtils.substringBefore(getCondition(expression), "-");
        return StringUtils.isNumeric(before) ? Integer.parseInt(before) : 0;
    }

    /**
     * 获取表达式中最大的值，为 Long 类型
     *
     * @param expression 表达式
     * @return 结果
     */
    private Long getMax(String expression) {
        String max = StringUtils.substringAfter(getCondition(expression), "-");
        return StringUtils.isNumeric(max) ? Long.parseLong(max) : Long.MAX_VALUE;
    }

    /**
     * 获取表达式中最小的值，为 Long 类型
     *
     * @param expression 表达式
     * @return 结果
     */
    private Long getMin(String expression) {
        String min = StringUtils.substringAfter(getCondition(expression), "-");
        return StringUtils.isNumeric(min) ? Long.parseLong(min) : Long.MIN_VALUE;
    }
}