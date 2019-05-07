package cn.edu.gzmu.auth.res;

import cn.edu.gzmu.constant.HttpMethod;
import cn.edu.gzmu.model.entity.SysRes;
import cn.edu.gzmu.model.entity.SysRole;
import cn.edu.gzmu.properties.Oauth2Properties;
import cn.edu.gzmu.repository.entity.SysResRepository;
import cn.edu.gzmu.repository.entity.SysRoleRepository;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 动态权限配置核心，将会对请求进行进行匹配
 * <p>
 * 对请求匹配后，赋予对应的角色。
 *
 * @author echo
 * @version 1.0
 * @date 19-4-20 16:15
 */
@Component
public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final SysResRepository sysResRepository;
    private final SysRoleRepository sysRoleRepository;
    private final Oauth2Properties oauth2Properties;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public SecurityMetadataSource(SysResRepository sysResRepository, SysRoleRepository sysRoleRepository, Oauth2Properties oauth2Properties) {
        this.sysResRepository = sysResRepository;
        this.sysRoleRepository = sysRoleRepository;
        this.oauth2Properties = oauth2Properties;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest httpRequest = ((FilterInvocation) object).getHttpRequest();
        String method = httpRequest.getMethod();
        String requestUrl = httpRequest.getServletPath();
        if (isRoleAdmin() || !oauth2Properties.isEnabled()) {
            // 对于管理员角色和不启用的情况，开放所有资源
            return SecurityConfig.createList("ROLE_PUBLIC");
        }
        List<SysRes> sysRes = sysResRepository.findAll();
        for (SysRes res : sysRes) {
            // 路径匹配
            if (!antPathMatcher.match(res.getMatchUrl(), requestUrl)) {
                continue;
            }
            // 方法匹配
            if (!HttpMethod.ALL.match(res.getMethod()) && !method.equals(res.getMethod())) {
                continue;
            }
            // 角色匹配
            List<SysRole> sysRoles = sysRoleRepository.searchBySysResId(res.getId());
            if (CollectionUtils.isEmpty(sysRoles)) {
                continue;
            }
            List<String> roles = sysRoles.stream().map(SysRole::getName).collect(Collectors.toList());
            if (roles.contains("ROLE_PUBLIC")) {
                return SecurityConfig.createList("ROLE_PUBLIC");
            }
            return SecurityConfig.createList(roles.toArray(new String[roles.size()]));
        }
        return SecurityConfig.createList("ROLE_NO_AUTH");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    /**
     * 判断当前用户是否是管理员
     *
     * @return 是否是管理员
     */
    private boolean isRoleAdmin() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getAuthorities()
                    .contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return false;
    }
}
