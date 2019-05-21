package cn.edu.gzmu.config.oauth2;

import cn.edu.gzmu.auth.res.AuthAccessDecisionManager;
import cn.edu.gzmu.auth.sms.SmsAuthenticationSecurityConfig;
import cn.edu.gzmu.properties.Oauth2Properties;
import cn.edu.gzmu.validate.ValidateCodeSecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 资源服务器
 *
 * @author echo
 * @version 1.0
 * @date 19-4-16 20:45
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private final ValidateCodeSecurityConfig validateCodeSecurityConfig;
    private final SmsAuthenticationSecurityConfig smsAuthenticationSecurityConfig;
    private final Oauth2Properties oauth2Properties;
    private final FilterInvocationSecurityMetadataSource securityMetadataSource;
    private final AuthAccessDecisionManager authAccessDecisionManager;

    public ResourceServerConfig(ValidateCodeSecurityConfig validateCodeSecurityConfig, SmsAuthenticationSecurityConfig smsAuthenticationSecurityConfig,
                                Oauth2Properties oauth2Properties, FilterInvocationSecurityMetadataSource securityMetadataSource,
                                AuthAccessDecisionManager authAccessDecisionManager) {
        this.validateCodeSecurityConfig = validateCodeSecurityConfig;
        this.smsAuthenticationSecurityConfig = smsAuthenticationSecurityConfig;
        this.oauth2Properties = oauth2Properties;
        this.securityMetadataSource = securityMetadataSource;
        this.authAccessDecisionManager = authAccessDecisionManager;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(oauth2Properties.getResourceId());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsAuthenticationSecurityConfig);
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
}
