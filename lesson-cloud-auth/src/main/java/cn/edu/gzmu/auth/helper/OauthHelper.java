package cn.edu.gzmu.auth.helper;

import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.model.entity.SysRole;
import cn.edu.gzmu.model.entity.SysUser;
import cn.edu.gzmu.model.entity.Teacher;
import cn.edu.gzmu.model.exception.UserNotFoundException;
import cn.edu.gzmu.service.helper.UserContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;


/**
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2019/8/1 上午11:20
 */
@SuppressWarnings("all")
public class OauthHelper {

    /**
     * 获取当前用户信息
     *
     * @return 当前用户
     */
    public static SysUser currentUser() {
        return excludeNull(UserContext::currentUser);
    }

    /**
     * 当前用户是否拥有指定角色
     *
     * @param roleName 角色名称
     * @return 是否拥有
     */
    public boolean hasRole(@NotNull String roleName) {
        return excludeNull(false, userContext -> userContext.hasRole(roleName));
    }

    /**
     * 当前用户是否拥有指定角色
     *
     * @param sysRole 角色
     * @return 是否拥有
     */
    public boolean hasRole(@NotNull SysRole sysRole) {
        return excludeNull(false, userContext -> userContext.hasRole(sysRole));
    }

    /**
     * 获取当前用户的角色列表
     *
     * @return 角色列表
     */
    public static List<SysRole> currentRoles() {
        return excludeNull(UserContext::currentRoles);
    }

    /**
     * 当前用户是否为教师橘色
     *
     * @return 结果
     */
    public static boolean isTeacher() {
        return excludeNull(false, UserContext::isTeacher);
    }

    /**
     * 当前用户是否为学生橘色
     *
     * @return 结果
     */
    public static boolean isStudent() {
        return excludeNull(false, UserContext::isStudent);
    }

    /**
     * 当前用户是否为管理员角色
     *
     * @return 结果
     */
    public static boolean isAdmin() {
        return excludeNull(false, UserContext::isAdmin);
    }

    /**
     * 获取教师实体
     *
     * @return 教师实体
     */
    public static Teacher teacher() {
        return excludeNull(UserContext::getTeacher);
    }

    /**
     * 获取学生实体
     *
     * @return 学生实体
     */
    public static Student student() {
        return excludeNull(UserContext::getStudent);
    }

    /**
     * 获取实体
     *
     * @return 实体
     */
    @Nullable
    public static Object entity() {
        UserContext userContext = userContext();
        if (Objects.isNull(userContext) || isAnonymousUser()) {
            return null;
        }
        if (userContext.isTeacher()) {
            return userContext.getTeacher();
        } else if (userContext.isStudent()) {
            return userContext.getStudent();
        } else if (userContext.isAdmin()) {
            return null;
        } else {
            throw new UserNotFoundException("Can not find the entity of the user.");
        }
    }

    /**
     * 获取用户解密后的信息
     *
     * @return 用户信息
     */
    @Nullable
    private static UserContext userContext() {
//        Object current = SecurityContextHolder.getContext().getAuthentication().getDetails();
//        if (isAnonymousUser() || !(current instanceof OAuth2AuthenticationDetails)) {
//            return null;
//        }
//        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) current;
//        UserContext userContext = (UserContext) details.getDecodedDetails();
//        if (Objects.isNull(userContext)) {
//            throw new UserNotFoundException("Can not find the user context of the user.");
//        }
//        return userContext;
        return null;
    }

    /**
     * 是否是匿名用户
     *
     * @return 结果
     */
    public static boolean isAnonymousUser() {
        final String anonymousUser = "anonymousUser";
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals(anonymousUser);
    }


    /**
     * 非空排除
     *
     * @param defaultValue 默认值
     * @param fun          函数型接口
     * @param <T>          类型
     * @return 结果
     */
    private static <T> T excludeNull(T defaultValue, Function<UserContext, T> fun) {
        UserContext userContext = userContext();
        return Objects.isNull(userContext)
                ? defaultValue
                : fun.apply(userContext);
    }

    /**
     * 非空排除
     *
     * @param fun 函数型接口
     * @param <T> 类型
     * @return 结果
     */
    private static <T> T excludeNull(Function<UserContext, T> fun) {
        return excludeNull(null, fun);
    }

}
