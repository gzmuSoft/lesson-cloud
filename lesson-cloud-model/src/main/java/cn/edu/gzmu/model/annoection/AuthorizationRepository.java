package cn.edu.gzmu.model.annoection;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 *
 * @date 2019/8/7 下午2:23
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface AuthorizationRepository {
}
