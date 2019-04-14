package cn.edu.gzmu.validate;

import cn.edu.gzmu.auth.AuthFailureHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-14 10:56
 */
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter {

    private AuthFailureHandle authFailureHandle;

    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.endsWithIgnoreCase("/login", request.getRequestURI())
                && StringUtils.endsWithIgnoreCase("post", request.getMethod())) {
            try {
                log.info("是登录请求！");
                validate(request);
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

    public void setAuthFailureHandle(AuthFailureHandle authFailureHandle) {
        this.authFailureHandle = authFailureHandle;
    }

}
