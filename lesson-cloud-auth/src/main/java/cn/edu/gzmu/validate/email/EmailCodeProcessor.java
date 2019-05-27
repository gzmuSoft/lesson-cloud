package cn.edu.gzmu.validate.email;

import cn.edu.gzmu.constant.SecurityConstants;
import cn.edu.gzmu.validate.ValidateCode;
import cn.edu.gzmu.validate.ValidateCodeSender;
import cn.edu.gzmu.validate.impl.AbstractValidateCodeProcessor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author echo
 * @version 1.0
 * @date 19-5-21 23:52
 */
@Component("emailValidateCodeProcessor")
@RequiredArgsConstructor
public class EmailCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {
    private final @NonNull ValidateCodeSender mailCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        mailCodeSender.send(request.getHeader(SecurityConstants.PARAMETER_EMAIL), validateCode);
    }
}
