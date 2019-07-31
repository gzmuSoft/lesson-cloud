package cn.edu.gzmu.auth.res;

import cn.edu.gzmu.auth.helper.UserContext;
import cn.edu.gzmu.constant.HttpMethod;
import cn.edu.gzmu.model.constant.EntityType;
import cn.edu.gzmu.model.entity.*;
import cn.edu.gzmu.properties.Oauth2Properties;
import cn.edu.gzmu.repository.entity.SysResRepository;
import cn.edu.gzmu.repository.entity.SysRoleResRepository;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.security.oauth2.provider.token.AccessTokenConverter.*;

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
    private final static Long ROLE_PUBLIC_ID = -1L;
    private final @NonNull SysResRepository sysResRepository;
    private final @NonNull SysRoleResRepository sysRoleResRepository;
    private final @NonNull Oauth2Properties oauth2Properties;
    private final @NonNull JwtAccessTokenConverter jwtAccessTokenConverter;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest httpRequest = ((FilterInvocation) object).getHttpRequest();
        String method = httpRequest.getMethod();
        String requestUrl = httpRequest.getServletPath();
        if (isRoleAdmin() || !oauth2Properties.isEnabled()) {
            // 对于管理员角色和不启用的情况，开放所有资源
            return SecurityConfig.createList("ROLE_PUBLIC");
        }
        decodeUserDetails();
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
            // 获取匹配当前资源的角色 id 表
            List<Long> sysRoleIds = sysRoleResList.stream().map(SysRoleRes::getRoleId).collect(Collectors.toList());
            // 如果当前匹配的角色中含有公共资源的 id （-1）
            if (sysRoleIds.contains(ROLE_PUBLIC_ID)) {
                return SecurityConfig.createList("ROLE_PUBLIC");
            }
            // 获取当前用户的角色信息
            List<SysRole> sysRoles = ((UserContext) SecurityContextHolder.getContext().getAuthentication().getDetails()).currentRoles();
            // 检查当前用户的角色是否有符合当前资源的
            List<SysRole> matchRoles = sysRoles.stream().filter(sysRole -> sysRoleIds.contains(sysRole.getId())).collect(Collectors.toList());
            if (matchRoles.size() == 0) {
                continue;
            }
            return SecurityConfig.createList(matchRoles.stream().map(SysRole::getName).collect(Collectors.joining(",")));
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
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    /**
     * 解密用户信息，直接解析 jwt token
     * 把解析后的信息存入 {@link SecurityContextHolder#getContext()} 中
     * 通过 <code>SecurityContextHolder.getContext().getAuthentication().getDetails()</code>
     * 获取当前 {@link UserContext} 实例，需要强转
     * 通过 {@link UserContext#getSysUser()} 获取当前用户
     */
    private void decodeUserDetails() {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext()
                .getAuthentication().getDetails();
        String tokenValue = details.getTokenValue();
        if (StringUtils.isNotBlank(tokenValue)) {
            // 已经标记过时的类
            // 需要启用请注入 JwtAccessTokenConverter 进行解密和认证
            // Jwt decode = JwtHelper.decodeAndVerify(tokenValue,
            //         new RsaVerifier(ResourceServerConfig.JwtKey.publicKey));
            Jwt decode = JwtHelper.decodeAndVerify(tokenValue,
                    new RsaVerifier(jwtAccessTokenConverter.getKey().get("value")));
            JSONObject oauth = JSONObject.parseObject(decode.getClaims());
            if (oauth.containsKey(EXP) && oauth.get(AccessTokenConverter.EXP) instanceof Integer) {
                Integer intValue = (Integer) oauth.get(EXP);
                oauth.put(EXP, new Long(intValue));
            }
            details.setDecodedDetails(userDetails(oauth));
//            AbstractAuthenticationToken authentication = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//            authentication.setDetails(userDetails(oauth));
        }
    }

    /**
     * 创建用户上下文对象
     *
     * @param jwtInfo jwt 解密信息
     * @return 用户上下文对象
     */
    private UserContext userDetails(@NotNull JSONObject jwtInfo) {
        UserContext userContext = new UserContext();
        SysUser user = jwtInfo.getObject("user_info", SysUser.class);
        Assert.notNull(user, "The user info no found！");
        userContext.setSysUser(user);
        JSONArray roles = jwtInfo.getJSONArray("role_info");
        List<SysRole> sysRoles = roles.toJavaList(SysRole.class);
        userContext.setSysRoles(sysRoles);
        String entityInfo = jwtInfo.getString("entity_info");
        sysRoles.forEach(role -> {
            if (EntityType.isAdmin(role.getName())) {
                userContext.setAdmin(true);
            }
            if (StringUtils.isBlank(entityInfo)) {
                return;
            }
            if (EntityType.isTeacher(role.getName())) {
                Teacher teacher = JSONObject.parseObject(entityInfo, Teacher.class);
                userContext.entity(teacher);
            }
            if (EntityType.isStudent(role.getName())) {
                Student student = JSONObject.parseObject(entityInfo, Student.class);
                userContext.entity(student);
            }
        });
        return userContext;
    }

}
