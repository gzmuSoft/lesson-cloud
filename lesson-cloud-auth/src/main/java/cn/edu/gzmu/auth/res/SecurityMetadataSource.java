package cn.edu.gzmu.auth.res;

import cn.edu.gzmu.constant.HttpMethod;
import cn.edu.gzmu.model.entity.*;
import cn.edu.gzmu.properties.Oauth2Properties;
import cn.edu.gzmu.repository.entity.SysResRepository;
import cn.edu.gzmu.repository.entity.SysRoleResRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static cn.edu.gzmu.model.constant.Security.*;

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
@RequiredArgsConstructor
public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private final @NonNull SysResRepository sysResRepository;
    private final @NonNull SysRoleResRepository sysRoleResRepository;
    private final @NonNull Oauth2Properties oauth2Properties;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest httpRequest = ((FilterInvocation) object).getHttpRequest();
        String method = httpRequest.getMethod();
        String requestUrl = httpRequest.getServletPath();
        if (isRoleAdmin() || !oauth2Properties.isEnabled()) {
            // 对于管理员角色和不启用的情况，开放所有资源
            return SecurityConfig.createList(ROLE_PUBLIC);
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
            // 获取匹配当前资源的角色资源关联表
            List<SysRoleRes> sysRoleResList = sysRoleResRepository.findAllByResId(res.getId());
            if (CollectionUtils.isEmpty(sysRoleResList)) {
                continue;
            }
            // 获取匹配当前资源的角色名称表
            List<String> sysRoleNames = sysRoleResList.stream().map(SysRoleRes::getRoleName).collect(Collectors.toList());
            if (sysRoleNames.contains(ROLE_PUBLIC)) {
                return SecurityConfig.createList(ROLE_PUBLIC);
            } else if (sysRoleNames.contains(ROLE_NO_LOGIN)) {
                return SecurityConfig.createList(ROLE_NO_LOGIN);
            }
            return SecurityConfig.createListFromCommaDelimitedString(String.join(",", sysRoleNames));
        }
        return SecurityConfig.createList(ROLE_NO_AUTH);
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
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .contains(new SimpleGrantedAuthority(ROLE_ADMIN));
    }

}
