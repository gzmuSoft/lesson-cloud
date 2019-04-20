package cn.edu.gzmu.validate.impl;

import cn.edu.gzmu.constant.ValidateCodeType;
import cn.edu.gzmu.validate.*;
import cn.edu.gzmu.validate.exception.ValidateCodeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.xml.bind.ValidationException;
import java.util.Map;

/**
 * 默认抽象的验证码处理器，不同类型的验证码必须继承此类。
 * <p>
 * 提供了一套默认完整的生成、保存操作,对于不同的类型会有不同的发送操作，因此子类必须实现
 * 抽象 <code>send</code> 方法
 * 具体请参阅每个方法的详细注释
 *
 * @author echo
 * @version 1.0
 * @date 19-4-14 11:37
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode>
        implements ValidateCodeProcessor {

    @Autowired
    private ValidateCodeRepository validateRepository;

    /**
     * 收集系统中所有的 {@link ValidateCodeGenerator} 接口实现。
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    /**
     * 验证码创建逻辑
     *
     * @param request 请求
     * @throws Exception 异常
     */
    @Override
    public void create(ServletWebRequest request) throws Exception {
        C generate = generate(request);
        save(request, generate);
        send(request, generate);
    }

    /**
     * 生成验证码
     *
     * @param request 请求
     * @return 验证码
     */
    @SuppressWarnings("unchecked")
    private C generate(ServletWebRequest request) throws ValidationException {
        String type = getValidateCodeType().toString().toLowerCase();
        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
        if (validateCodeGenerator == null) {
            throw new ValidationException("验证码生成器" + generatorName + "不存在。");
        }
        return (C) validateCodeGenerator.generate(request);
    }

    /**
     * 验证器验证验证码
     *
     * @param request 被验证的请求
     */
    @Override
    @SuppressWarnings("unchecked")
    public void validate(ServletWebRequest request) {
        ValidateCodeType validateCodeType = getValidateCodeType();
        C code = (C) validateRepository.get(request, validateCodeType);
        try {
            if (!StringUtils.equalsIgnoreCase(code.getCode(), ServletRequestUtils.getStringParameter(request.getRequest(),
                    validateCodeType.getParamNameOnValidate()))) {
                throw new ValidateCodeException("验证码不正确，请重新输入！");
            }
        } catch (Exception e) {
            throw new ValidateCodeException("获取验证码失败，请重新发送！");
        }
    }

    /**
     * 保存验证码
     *
     * @param request      请求
     * @param validateCode 验证码
     */
    private void save(ServletWebRequest request, C validateCode) {
        validateRepository.save(request, validateCode, ValidateCodeType.SMS);
    }

    /**
     * 发送验证码，由子类实现
     *
     * @param request      请求
     * @param validateCode 验证码
     * @throws Exception 异常
     */
    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    /**
     * 根据请求 url 获取验证码类型
     *
     * @return 结果
     */
    private ValidateCodeType getValidateCodeType() {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }
}
