package cn.edu.gzmu.config.oauth2;

import cn.edu.gzmu.auth.sms.SmsAuthenticationSecurityConfig;
import cn.edu.gzmu.constant.SecurityConstants;
import cn.edu.gzmu.properties.Oauth2Properties;
import cn.edu.gzmu.validate.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
    private final AuthenticationFailureHandler authFailureHandle;
    private final AuthenticationSuccessHandler authSuccessHandler;
    private final ValidateCodeSecurityConfig validateCodeSecurityConfig;
    private final SmsAuthenticationSecurityConfig smsAuthenticationSecurityConfig;
    private final Oauth2Properties oauth2Properties;

    public ResourceServerConfig(AuthenticationFailureHandler authFailureHandle, AuthenticationSuccessHandler authSuccessHandler, ValidateCodeSecurityConfig validateCodeSecurityConfig, SmsAuthenticationSecurityConfig smsAuthenticationSecurityConfig, Oauth2Properties oauth2Properties) {
        this.authFailureHandle = authFailureHandle;
        this.authSuccessHandler = authSuccessHandler;
        this.validateCodeSecurityConfig = validateCodeSecurityConfig;
        this.smsAuthenticationSecurityConfig = smsAuthenticationSecurityConfig;
        this.oauth2Properties = oauth2Properties;
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
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandle)
                .and()
                .authorizeRequests()
                .antMatchers(
                        SecurityConstants.LOGIN_PROCESSING_URL_SMS,
                        SecurityConstants.VALIDATE_CODE_URL_PREFIX + "/*"
                )
                .permitAll()
                .anyRequest()
                .authenticated();

    }
}
