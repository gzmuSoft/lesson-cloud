package cn.edu.gzmu.auth.user;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;

/**
 * 如果 {@link SmsUserDetailsService} 实现无法通过其 sms 设备号找到{@link User}，则抛出此异常。
 *
 * @author echo
 * @version 1.0
 * @date 19-4-20 13:40
 */
public class SmsNotFoundException extends AuthenticationException {
    /**
     * 构造自定义信息的 <code> SmsNotFoundException </code>。
     *
     * @param msg 细节信息
     */
    public SmsNotFoundException(String msg) {
        super(msg);
    }

    /**
     * 构造自定义信息异常的 <code> SmsNotFoundException </code>。
     *
     * @param msg 细节信息
     * @param t   异常
     */
    public SmsNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
