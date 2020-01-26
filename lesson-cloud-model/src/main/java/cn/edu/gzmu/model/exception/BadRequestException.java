package cn.edu.gzmu.model.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author BugRui
 * @date 2020/1/26 下午9:54
 **/
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BadRequestException extends RuntimeException {
    private String message;

    public BadRequestException() {
        this.message = "验证异常";
    }
}
