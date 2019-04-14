package cn.edu.gzmu.validate.sms;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-14 14:13
 */
public class SmsCodeSenderImpl implements SmsCodeSender {
    /**
     * 发送验证码
     *
     * @param phone 手机号
     * @param code   验证码
     */
    @Override
    public void send(String phone, String code) {
        System.out.println("向手机号" + phone + "发送" + code);
    }
}
