package cn.edu.gzmu.auth.helper;

import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.model.entity.SysRole;
import cn.edu.gzmu.model.entity.SysUser;
import cn.edu.gzmu.model.entity.Teacher;
import cn.edu.gzmu.model.exception.UserNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.List;
import java.util.Objects;


/**
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2019/8/1 上午11:20
 */
public class OauthHelper {

    /**
     * 获取当前用户信息
     *
     * @return 当前用户
     */
    public static SysUser currentUser() {
        return userContext().currentUser();
    }

    /**
     * 当前用户是否拥有指定角色
     *
     * @param roleName 角色名称
     * @return 是否拥有
     */
    public boolean hasRole(@NotNull String roleName) {
        return userContext().hasRole(roleName);
    }

    /**
     * 当前用户是否拥有指定角色
     *
     * @param sysRole 角色
     * @return 是否拥有
     */
    public boolean hasRole(@NotNull SysRole sysRole) {
        return userContext().hasRole(sysRole);
    }

    /**
     * 获取当前用户的角色列表
     *
     * @return 角色列表
     */
    public static List<SysRole> currentRoles() {
        return userContext().currentRoles();
    }

    /**
     * 当前用户是否为教师橘色
     *
     * @return 结果
     */
    public static boolean isTeacher() {
        return userContext().isTeacher();
    }

    /**
     * 当前用户是否为学生橘色
     *
     * @return 结果
     */
    public static boolean isStudent() {
        return userContext().isStudent();
    }

    /**
     * 当前用户是否为管理员角色
     *
     * @return 结果
     */
    public static boolean isAdmin() {
        return userContext().isAdmin();
    }

    /**
     * 获取教师实体
     *
     * @return 教师实体
     */
    public static Teacher teacher() {
        return userContext().getTeacher();
    }

    /**
     * 获取学生实体
     *
     * @return 学生实体
     */
    public static Student student() {
        return userContext().getStudent();
    }

    /**
     * 获取实体
     *
     * @return 实体
     */
    @Nullable
    public static Object entity() {
        UserContext userContext = userContext();
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
    private static UserContext userContext() {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext()
                .getAuthentication().getDetails();
        UserContext userContext = (UserContext) details.getDecodedDetails();
        if (Objects.isNull(userContext)) {
            throw new UserNotFoundException("Can not find the user context of the user.");
        }
        return userContext;
    }

}
