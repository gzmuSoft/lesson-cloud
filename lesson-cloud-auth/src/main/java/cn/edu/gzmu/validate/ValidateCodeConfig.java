package cn.edu.gzmu.validate;

import cn.edu.gzmu.util.EmailUtils;
import cn.edu.gzmu.util.SubMailUtils;
import cn.edu.gzmu.validate.email.EmailCodeSender;
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
    private final EmailUtils emailUtils;

    public ValidateCodeConfig(SubMailUtils subMailUtils, EmailUtils emailUtils) {
        this.subMailUtils = subMailUtils;
        this.emailUtils = emailUtils;
    }

    @Bean
    public ValidateCodeSender smsCodeSender() {
        return new SmsCodeSender(subMailUtils);
    }

    @Bean
    public ValidateCodeSender mailCodeSender() {
        return new EmailCodeSender(emailUtils);
    }

}
