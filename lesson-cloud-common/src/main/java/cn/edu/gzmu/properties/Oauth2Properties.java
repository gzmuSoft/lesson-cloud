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

    /**
     * 授权服务器地址
     */
    private String authorizationServerUrl = "http://127.0.0.1:8888";

    /**
     * 解析 token 地址
     */
    private String tokenInfoUri = "/oauth/check_token";

    /**
     * 用户信息
     */
    private String userInfoUri;

    /**
     * 授权服务器协议
     */
    private String scheme = "http";

    /**
     * 授权地址
     */
    private String authorizeUri = "/oauth/authorize";

    /**
     * 获取 token 地址
     */
    private String accessTokenUri = "/oauth/token";

    /**
     * 退出登录地址
     */
    private String logoutUri = "/oauth/logout";

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
     * 登录成功的回调地址
     */
    private String redirectUrl = "http://127.0.0.1:8081";

    /**
     * 退出登录的回调地址
     */
    private String logoutRedirectUrl = "http://127.0.0.1:8081";

    /**
     * 前台距离 token 多长时间刷新 token
     */
    private Long refreshTokenHour = 5L;

    /**
     * 授权域
     */
    private String scope;

    /**
     * jwkSetUri
     */
    private String jwkSetUri = "/.well-known/jwks.json";

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
