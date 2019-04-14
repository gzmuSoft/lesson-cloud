package cn.edu.gzmu.validate;

import cn.edu.gzmu.auth.handler.AuthFailureHandle;
import cn.edu.gzmu.constant.SecurityConstants;
import cn.edu.gzmu.constant.ValidateCodeType;
import com.sun.xml.internal.ws.api.policy.ValidationProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-14 10:56
 */
@Component
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationFailureHandler authFailureHandle;

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    private Map<String, ValidateCodeType> urlMap = new HashMap<>();

    private AntPathMatcher antPathMatcher = new AntPathMatcher();


    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.SMS);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        ValidateCodeType validateCodeType = getValidateCodeType(request);
        if (validateCodeType != null) {
            try {
                log.info("是登录请求！验证请求：" + request.getRequestURI() + "验证类型：" + validateCodeType);
                validateCodeProcessorHolder.findValidateCodeProcessor(validateCodeType)
                    .validate(new ServletWebRequest(request, response));
                logger.info("验证码通过！");
            } catch (ValidateCodeException e) {
                authFailureHandle.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validate(HttpServletRequest request) throws ServletRequestBindingException {
        String username = ServletRequestUtils.getStringParameter(request, "username");
        if ("user".equalsIgnoreCase(username)) {
            log.warn("帐号无效！");
            throw new ValidateCodeException("帐号无效！");
        }
    }

    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        ValidateCodeType validateCodeType = null;
        if (StringUtils.endsWithIgnoreCase(request.getMethod(), "GET")) {
            Set<String> urls = urlMap.keySet();
            for (String url : urls){
                if (antPathMatcher.match(url, request.getRequestURI())){
                    validateCodeType =  urlMap.get(url);
                }
            }
        }
        return validateCodeType;
    }

}
