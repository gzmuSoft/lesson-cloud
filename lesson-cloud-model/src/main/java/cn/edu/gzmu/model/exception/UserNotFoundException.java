package cn.edu.gzmu.model.exception;

/**
 * 用户未找到异常
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-21 10:43:57
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("用户不存在");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
