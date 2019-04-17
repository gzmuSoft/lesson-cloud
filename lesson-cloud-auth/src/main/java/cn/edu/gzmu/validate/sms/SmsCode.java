package cn.edu.gzmu.validate.sms;

import cn.edu.gzmu.validate.ValidateCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-14 14:07
 */
public class SmsCode extends ValidateCode implements Serializable {
    public SmsCode(String code, LocalDateTime expireTime) {
        super(code, expireTime);
    }

    public SmsCode() {
    }

    /**
     * 几秒后过期
     *
     * @param code     验证码
     * @param expireIn 过期时间，单位/秒
     */
    public SmsCode(String code, int expireIn) {
        super(code, expireIn);
    }
}
