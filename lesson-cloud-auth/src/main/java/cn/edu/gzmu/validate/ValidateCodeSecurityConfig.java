package cn.edu.gzmu.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;

/**
 * 验证码安全配置
 *
 * 添加过滤器
 *
 * @author echo
 * @version 1.0
 * @date 19-4-14 16:19
 */
@Component
public class ValidateCodeSecurityConfig
        extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final ValidateCodeFilter validateCodeFilter;

    public ValidateCodeSecurityConfig(ValidateCodeFilter validateCodeFilter) {
        this.validateCodeFilter = validateCodeFilter;
    }

    @Override
    public void configure(HttpSecurity http) {
        http.addFilterBefore(validateCodeFilter,
                AbstractPreAuthenticatedProcessingFilter.class);
    }
}
