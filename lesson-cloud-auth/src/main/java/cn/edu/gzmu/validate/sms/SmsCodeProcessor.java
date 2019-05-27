package cn.edu.gzmu.validate.sms;

import cn.edu.gzmu.constant.SecurityConstants;
import cn.edu.gzmu.validate.ValidateCode;
import cn.edu.gzmu.validate.ValidateCodeSender;
import cn.edu.gzmu.validate.impl.AbstractValidateCodeProcessor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-14 14:08
 */
@RequiredArgsConstructor
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    private final @NonNull ValidateCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws ServletRequestBindingException {
        smsCodeSender.send(request.getHeader(SecurityConstants.PARAMETER_SMS), validateCode);
    }

}
