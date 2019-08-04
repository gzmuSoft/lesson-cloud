package cn.edu.gzmu.config;

import cn.edu.gzmu.properties.Oauth2Properties;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2019/8/1 下午11:16
 */
@Configuration
@EnableOAuth2Client
@RequiredArgsConstructor
public class ClientConfig {

    private final @NonNull OAuth2ClientProperties oAuth2ClientProperties;
    private final @NonNull Oauth2Properties oauth2Properties;

    /**
     * 客户端请求
     * 对于需要携带客户端认证信息的请使用此操作
     *
     * @return RestTemplate
     */
    @Bean
    public RestTemplate oauth2RestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new BasicAuthenticationInterceptor(
                oAuth2ClientProperties.getClientId(),
                oAuth2ClientProperties.getClientSecret())
        );
        restTemplate.setInterceptors(interceptors);
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(
                oauth2Properties.getAuthorizationServerUrl()));
        return restTemplate;
    }

}
