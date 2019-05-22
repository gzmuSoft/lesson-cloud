package cn.edu.gzmu.validate.sms;

import cn.edu.gzmu.util.SubMailUtils;
import cn.edu.gzmu.validate.ValidateCode;
import cn.edu.gzmu.validate.ValidateCodeSender;
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

    public SmsCodeSender(SubMailUtils subMailUtils) {
        this.subMailUtils = subMailUtils;
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
        jsonObject.put("time", Duration.ofSeconds(code.getExpireIn()).toMinutes());
        subMailUtils.sendActionMessage(receive, jsonObject);
    }
}
