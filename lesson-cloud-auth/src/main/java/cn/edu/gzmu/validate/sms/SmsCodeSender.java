package cn.edu.gzmu.validate.sms;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-14 14:14
 */
public interface SmsCodeSender {

    /**
     * 发送验证码
     *
     * @param mobile 手机号
     * @param code   验证码
     */
    void send(String mobile, String code);
}
