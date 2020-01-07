package cn.edu.gzmu.config;

import cn.edu.gzmu.auth.res.AuthAccessDecisionManager;
import cn.edu.gzmu.properties.Oauth2Properties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.Base64;

/**
 * web 安全配置，在这里主要配额一些 Bean
 *
 * @author echo
 * @version 1.0
 * @date 19-4-14 10:43
 */
@Slf4j
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final FilterInvocationSecurityMetadataSource securityMetadataSource;
    private final AuthAccessDecisionManager authAccessDecisionManager;
    private final Oauth2Properties oauth2Properties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
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
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
                .jwkSetUri(oauth2Properties.getJwkSetUri());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 加密客户端 Basic 头信息
     *
     * @return client
     */
    public String encodeClient() {
        String client = oauth2Properties.getClientId()
                + ":" + oauth2Properties.getClientSecret();
        log.debug("client {}", client);
        return "Basic " + Base64.getEncoder().encodeToString(client.getBytes());
    }

}
