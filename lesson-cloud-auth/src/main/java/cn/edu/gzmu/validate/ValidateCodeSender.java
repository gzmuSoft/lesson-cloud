package cn.edu.gzmu.validate;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-14 14:14
 */
public interface ValidateCodeSender {

    /**
     * 发送验证码
     *
     * @param phone 手机号
     * @param code   验证码
     */
    void send(String phone, String code);
}
