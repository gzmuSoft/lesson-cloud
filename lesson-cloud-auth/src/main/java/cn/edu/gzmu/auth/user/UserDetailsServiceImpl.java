package cn.edu.gzmu.auth.user;

import cn.edu.gzmu.model.entity.SysRole;
import cn.edu.gzmu.model.entity.SysUser;
import cn.edu.gzmu.repository.entity.SysRoleRepository;
import cn.edu.gzmu.repository.entity.SysUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 用户服务，根据不同的策略查询不同的用户。
 * <p>通过实现不同的接口完成认证</p>
 *
 * @author echo
 * @version 1.0
 * @date 19-4-14 10:49
 * @see cn.edu.gzmu.config.oauth2.AuthorizationServerConfig
 */
@Slf4j
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService, SocialUserDetailsService,
        SmsUserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final SysUserRepository sysUserRepository;
    private final SysRoleRepository sysRoleRepository;

    public UserDetailsServiceImpl(PasswordEncoder passwordEncoder, SysUserRepository sysUserRepository,
                                  SysRoleRepository sysRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.sysUserRepository = sysUserRepository;
        this.sysRoleRepository = sysRoleRepository;
    }

    /**
     * 通过用户名查找用户，这是对密码登录的仅有支持。
     *
     * <p>
     * 在对应的容器中，他会自己调用 {@link UserDetailsService} 接口实现
     * </p>
     *
     * @param username 用户名
     * @return 用户信息
     * @throws UsernameNotFoundException 用户位置哦到
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username login user: {}", username);
        // 由于用户不复杂所以不在去自己构建 UserDetails 的实现类了。
        return loadUser(() -> sysUserRepository.findFirstByName(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("The user %s not found!", username))));
    }

    /**
     * 通过设备号查找用户，这是对设备登录的仅有支持
     * <p>
     * 注意，在这里并不会验证密码，sms 登录仅对验证吗进行验证
     * 具体验证规则见 {@link cn.edu.gzmu.validate.impl.AbstractValidateCodeProcessor}
     * 需要修改请继承此类并覆盖其方法
     *
     * @param sms sms 设备号
     * @return 用户
     * @throws SmsNotFoundException sms 未找到异常
     */
    @Override
    public UserDetails loadUserBySms(String sms) throws SmsNotFoundException {
        log.info("sms login user: {}", sms);
        return loadUser(() -> sysUserRepository.findFirstByPhone(sms).orElseThrow(() ->
                new SmsNotFoundException(String.format("The user %s not found!", sms))));
    }

    /**
     * 加载用户，具体加载操作通过函数式接口进行完成，由调用方提供。
     *
     * @param load Supplier，直接获取一个结果
     * @return 用户
     */
    private User loadUser(Supplier<SysUser> load) {
        SysUser sysUser = load.get();
        List<SysRole> sysRoles = sysRoleRepository.searchBySysUserId(sysUser.getId());
        List<SimpleGrantedAuthority> authorities = sysRoles.stream().map(sysRole ->
                new SimpleGrantedAuthority(sysRole.getName())).collect(Collectors.toList());
        return new User(sysUser.getName(), sysUser.getPwd(), authorities);
    }

    /**
     * 社交登录，暂时未做
     *
     * @param userId 通过 oauth2 获取到的用户 id
     * @return 用户信息
     * @throws UsernameNotFoundException 用户未找到
     */
    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        log.info("social login user: {}", userId);
        return new SocialUser(userId, passwordEncoder.encode("123456"),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_user"));
    }

}
