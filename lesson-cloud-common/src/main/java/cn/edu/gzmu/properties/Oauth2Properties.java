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
@ConfigurationProperties("application.oauth2")
public class Oauth2Properties {
    /**
     * 是否启用，启用后，不做角色认证
     */
    private boolean enabled = true;

    private String authorizationServerUrl = "http://127.0.0.1:8888";

    /**
     * 解析 token 地址
     */
    private String tokenInfoUri;

    /**
     * 获取 token 地址
     */
    private String accessTokenUri;

    /**
     * 客户端 id
     */
    private String clientId;

    /**
     * 客户端密钥
     */
    private String clientSecret;

    /**
     * 授权类型
     */
    private String grantType;

    /**
     * 授权域
     */
    private String scope;

    /**
     * jwkSetUri
     */
    private String jwkSetUri;

    public String getTokenInfoUri() {
        return authorizationServerUrl + tokenInfoUri;
    }

    public String getAccessTokenUri() {
        return authorizationServerUrl + accessTokenUri;
    }

    public String getJwkSetUri() {
        return authorizationServerUrl + jwkSetUri;
    }
}
