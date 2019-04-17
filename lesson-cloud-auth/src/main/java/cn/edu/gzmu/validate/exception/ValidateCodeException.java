package cn.edu.gzmu.validate.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常
 *
 * @author echo
 * @version 1.0
 * @date 19-4-14 11:14
 */
public class ValidateCodeException extends AuthenticationException {
    /**
     * Constructs an {@code AuthenticationException} with the specified message and root
     * cause.
     *
     * @param msg the detail message
     * @param t   the root cause
     */
    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    /**
     * Constructs an {@code AuthenticationException} with the specified message and no
     * root cause.
     *
     * @param msg the detail message
     */
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
