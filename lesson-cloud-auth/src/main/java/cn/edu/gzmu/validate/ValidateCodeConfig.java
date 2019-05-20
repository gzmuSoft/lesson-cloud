package cn.edu.gzmu.validate;

import cn.edu.gzmu.config.SmsProperties;
import cn.edu.gzmu.util.SubMailUtils;
import cn.edu.gzmu.validate.sms.SmsCodeSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-14 14:17
 */
@Configuration
public class ValidateCodeConfig {

    private final SubMailUtils subMailUtils;
    private final SmsProperties smsProperties;

    public ValidateCodeConfig(SubMailUtils subMailUtils, SmsProperties smsProperties) {
        this.subMailUtils = subMailUtils;
        this.smsProperties = smsProperties;
    }

    @Bean
    public ValidateCodeSender smsCodeSender(){
        return new SmsCodeSender(subMailUtils, smsProperties);
    }
}
