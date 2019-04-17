package cn.edu.gzmu.validate.sms;

import cn.edu.gzmu.validate.ValidateCodeSender;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-14 14:13
 */
public class SmsCodeSender implements ValidateCodeSender {
    /**
     * 发送验证码
     *
     * @param sms 手机号
     * @param code   验证码
     */
    @Override
    public void send(String sms, String code) {
        System.out.println("向手机号" + sms + "发送" + code);
    }
}
