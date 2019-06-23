package cn.edu.gzmu.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-18 21:54
 */
@Data
@Component
@ConfigurationProperties("application.security")
public class Oauth2Properties {
    /**
     * 是否启用，启用后，不做角色认证
     */
    private boolean enabled = true;
}
