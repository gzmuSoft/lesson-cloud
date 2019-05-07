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
     * 客户端 id
     */
    private String clientId;
    /**
     * 客户端加密后密钥
     */
    private String clientSecret;
    /**
     * 资源 id
     */
    private String resourceId;
    /**
     * access Token 有效期
     */
    private Long accessTokenValidityMinutes;
    /**
     * refresh Token 有效期
     */
    private Long refreshTokenValidityMinutes;
    /**
     * scope
     */
    private String scope;
    /**
     * 是否启用，启用后，不做角色认证
     */
    private boolean enabled = true;
    /**
     * 重要！！！jwt签名密钥，不可公开！
     */
    private String jwtSigningKey;
}
