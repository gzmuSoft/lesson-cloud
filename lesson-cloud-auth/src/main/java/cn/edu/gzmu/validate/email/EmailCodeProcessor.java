package cn.edu.gzmu.validate.email;

import cn.edu.gzmu.constant.SecurityConstants;
import cn.edu.gzmu.validate.ValidateCode;
import cn.edu.gzmu.validate.ValidateCodeSender;
import cn.edu.gzmu.validate.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author echo
 * @version 1.0
 * @date 19-5-21 23:52
 */
@Component("emailValidateCodeProcessor")
public class EmailCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {
    private final ValidateCodeSender mailCodeSender;

    public EmailCodeProcessor(ValidateCodeSender mailCodeSender) {
        this.mailCodeSender = mailCodeSender;
    }

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        mailCodeSender.send(request.getHeader(SecurityConstants.PARAMETER_EMAIL), validateCode);
    }
}
