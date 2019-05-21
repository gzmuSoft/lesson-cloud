package cn.edu.gzmu.validate.sms;

import cn.edu.gzmu.config.SmsProperties;
import cn.edu.gzmu.util.SubMailUtils;
import cn.edu.gzmu.validate.ValidateCode;
import cn.edu.gzmu.validate.ValidateCodeSender;
import cn.edu.gzmu.validate.exception.ValidateCodeException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

/**
 * 验证码发送
 * <p>
 * 对于他的注入，请在 {@link cn.edu.gzmu.validate.ValidateCodeConfig} 中进行配置
 * 使用 CGLIB 增强
 *
 * @author echo
 * @version 1.0
 * @date 19-4-14 14:13
 */
@Slf4j
public class SmsCodeSender implements ValidateCodeSender {

    private final SubMailUtils subMailUtils;
    private final SmsProperties smsProperties;

    public SmsCodeSender(SubMailUtils subMailUtils, SmsProperties smsProperties) {
        this.subMailUtils = subMailUtils;
        this.smsProperties = smsProperties;
    }

    /**
     * 发送验证码
     *
     * @param receive 手机号
     * @param code    验证码
     */
    @Override
    public void send(String receive, ValidateCode code) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action", "登录");
        jsonObject.put("code", code.getCode());
        jsonObject.put("time", Duration.ofSeconds(smsProperties.getCodeExpireIn()).toMinutes());
        boolean b = subMailUtils.sendActionMessage(receive, jsonObject);
        if (b) {
            log.info("向手机号 {} 发送登录验证码 {} 成功！有效期至 {}", receive, code.getCode(), code.getExpireTime());
        } else {
            log.warn("向手机号 {} 发送登录验证码 {} 失败", receive, code.getCode());
            throw new ValidateCodeException(String.format("向手机号 %s 发送登录验证码失败", receive));
        }
    }
}
