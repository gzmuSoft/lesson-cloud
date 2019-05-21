package cn.edu.gzmu.config.oauth2;

import cn.edu.gzmu.auth.user.UserDetailsServiceImpl;
import cn.edu.gzmu.properties.Oauth2Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.time.Duration;

/**
 * 授权服务器配置
 *
 * @author echo
 * @version 1.0
 * @date 19-4-15 22:37
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final TokenStore tokenStore;
    private final Oauth2Properties oauth2Properties;

    public AuthorizationServerConfig(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, TokenStore tokenStore, Oauth2Properties oauth2Properties) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.tokenStore = tokenStore;
        this.oauth2Properties = oauth2Properties;
    }

    /**
     * 端点配置，在这里配置 token 存储以及用户处理
     *
     * @param endpoints 自动注入的端点
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore)
                .tokenStore(jwtTokenStore())
                .accessTokenConverter(jwtAccessTokenConverter());
    }

    /**
     * 配置库客户端授权信息
     *
     * @param clients 客户端配置，可以使用他对多个客户端进行配置
     * @throws Exception 异常
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(oauth2Properties.getClientId())
                .resourceIds(oauth2Properties.getResourceId())
                .secret(oauth2Properties.getClientSecret())
                .accessTokenValiditySeconds((int) Duration.ofMinutes(oauth2Properties.getAccessTokenValidityMinutes()).getSeconds())
                .refreshTokenValiditySeconds((int) Duration.ofMinutes(oauth2Properties.getRefreshTokenValidityMinutes()).getSeconds())
                .authorizedGrantTypes("refresh_token", "password")
                .scopes(oauth2Properties.getScope());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    /**
     * TokenServices
     *
     * @return Token 配置
     */
    @Bean
    @Primary
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(jwtTokenStore());
        return defaultTokenServices;
    }

    @Bean
    public JwtTokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey(oauth2Properties.getJwtSigningKey());
        return accessTokenConverter;
    }


}
