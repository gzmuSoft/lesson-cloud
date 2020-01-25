package cn.edu.gzmu.model.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author BugRui
 * @date 2020/1/25 下午8:55
 **/
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ValidateException extends RuntimeException {
    private String message;

    public ValidateException() {
        this.message = "验证异常";
    }
}
