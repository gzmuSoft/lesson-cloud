package cn.edu.gzmu.model.exception;

/**
 * .
 *
 *
 * @date 2020/1/8 下午1:48
 */
public class AuthorizationServerException extends RuntimeException {

    public AuthorizationServerException() {
        super("授权服务器异常！");
    }

    public AuthorizationServerException(String message) {
        super(message);
    }

    public AuthorizationServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorizationServerException(String message, Object... vars) {
        super(String.format(message, vars));
    }

}
