package cn.edu.gzmu.model.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * BaseEntity
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-20 16:47:50
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResourceException extends RuntimeException {
    private String message;

    public ResourceException() {
        this.message = "资源异常";
    }
}
