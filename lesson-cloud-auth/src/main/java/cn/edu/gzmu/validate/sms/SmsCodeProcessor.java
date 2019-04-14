package cn.edu.gzmu.validate.sms;

import cn.edu.gzmu.constant.ValidateCodeType;
import cn.edu.gzmu.validate.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-14 14:08
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<SmsValidateCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;

    /**
     * 发送验证码，由子类实现
     *
     * @param request      请求
     * @param validateCode 验证码
     */
    @Override
    protected void send(ServletWebRequest request, SmsValidateCode validateCode) throws ServletRequestBindingException {
        String phone = ServletRequestUtils.getStringParameter(request.getRequest(), "phone");
        smsCodeSender.send(phone, validateCode.getCode());
    }



}
