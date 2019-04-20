package cn.edu.gzmu.validate;

import cn.edu.gzmu.constant.SecurityConstants;
import cn.edu.gzmu.constant.ValidateCodeType;
import cn.edu.gzmu.validate.exception.ValidateCodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 验证码过滤器。
 *
 * <p>继承于 {@link OncePerRequestFilter} 确保在一次请求只通过一次filter</p>
 * <p>需要配置指定拦截路径，默认拦截 POST 请求</p>
 *
 * @author echo
 * @version 1.0
 * @date 19-4-14 10:56
 */
@Component
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter {

    private final AuthenticationFailureHandler authFailureHandle;
    private final ValidateCodeProcessorHolder validateCodeProcessorHolder;
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public ValidateCodeFilter(AuthenticationFailureHandler authFailureHandle, ValidateCodeProcessorHolder validateCodeProcessorHolder) {
        this.authFailureHandle = authFailureHandle;
        this.validateCodeProcessorHolder = validateCodeProcessorHolder;
    }

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        // 路径拦截
        urlMap.put(SecurityConstants.LOGIN_PROCESSING_URL_SMS, ValidateCodeType.SMS);
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
                // 授权失败处理器接受处理
                authFailureHandle.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        // 放行
        filterChain.doFilter(request, response);
    }

    /**
     * 获取验证码类型
     *
     * @param request 请求
     * @return 验证码类型
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        if (StringUtils.endsWithIgnoreCase(request.getMethod(), HttpMethod.POST.name())) {
            Set<String> urls = urlMap.keySet();
            for (String url : urls){
                if (antPathMatcher.match(url, request.getRequestURI())){
                    return urlMap.get(url);
                }
            }
        }
        return null;
    }

}
