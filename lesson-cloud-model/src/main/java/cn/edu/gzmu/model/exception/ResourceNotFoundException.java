package cn.edu.gzmu.model.exception;

/**
 * 资源未找到异常
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-21 10:43:57
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("请求的资源不存在！");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
