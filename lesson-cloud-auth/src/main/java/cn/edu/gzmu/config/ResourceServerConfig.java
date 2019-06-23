package cn.edu.gzmu.config;

import cn.edu.gzmu.auth.res.AuthAccessDecisionManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

/**
 * 资源服务器
 *
 * @author echo
 * @version 1.0
 * @date 19-4-16 20:45
 */
@Configuration
@EnableOAuth2Client
@EnableResourceServer
@RequiredArgsConstructor
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private @NonNull final FilterInvocationSecurityMetadataSource securityMetadataSource;
    private @NonNull final AuthAccessDecisionManager authAccessDecisionManager;
    private @NonNull final ResourceServerProperties resourceServerProperties;

    private static final String DEMO_RESOURCE_ID = "lesson-cloud";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(DEMO_RESOURCE_ID).stateless(true)
                .tokenServices(tokenServices());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                .authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(securityMetadataSource);
                        o.setAccessDecisionManager(authAccessDecisionManager);
                        return o;
                    }
                })
                .anyRequest()
                .authenticated();
    }


    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setVerifierKey(getPubKey());
        return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

    private String getPubKey() {
        return StringUtils.isEmpty(resourceServerProperties.getJwt().getKeyValue())
                ? getKeyFromAuthorizationServer()
                : resourceServerProperties.getJwt().getKeyValue();
    }

    private String getKeyFromAuthorizationServer() {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", encodeClient());
        HttpEntity<String> requestEntity = new HttpEntity<>(null, httpHeaders);
        String pubKey = new RestTemplate()
                .getForObject(resourceServerProperties.getJwt().getKeyUri(), String.class, requestEntity);
        try {
            Map map = objectMapper.readValue(pubKey, Map.class);
            System.out.println("联网公钥");
            return map.get("value").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String encodeClient() {
        return "Basic " + Base64.getEncoder().encodeToString((resourceServerProperties.getClientId()
                + ":" + resourceServerProperties.getClientSecret()).getBytes());
    }
}
