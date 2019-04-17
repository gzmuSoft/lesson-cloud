package cn.edu.gzmu.validate;

import cn.edu.gzmu.constant.ValidateCodeType;
import cn.edu.gzmu.validate.exception.ValidateCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-14 16:22
 */
@Component
public class ValidateCodeProcessorHolder {
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.getParamNameOnValidate().toLowerCase());
    }

    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
        if (processor == null){
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return processor;
    }

}
