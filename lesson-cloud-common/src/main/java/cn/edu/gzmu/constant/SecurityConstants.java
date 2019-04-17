package cn.edu.gzmu.constant;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-14 16:17
 */
public interface SecurityConstants {

    /**
     * 理验证码的 url 前缀
     */
    public static final String VALIDATE_CODE_URL_PREFIX = "/code";
    /**
     * 用户名密码登录请求处理 url
     */
    public static final String LOGIN_PROCESSING_URL_FORM = "/oauth/token";
    /**
     * 手机验证码登录请求 url
     */
    public static final String LOGIN_PROCESSING_URL_SMS = "/oauth/sms";
    /**
     * 发送短信验证码或验证短信验证码时，手机号的参数名称
     */
    public static final String DEFAULT_PARAMETER_NAME_SMS = "sms";

}
