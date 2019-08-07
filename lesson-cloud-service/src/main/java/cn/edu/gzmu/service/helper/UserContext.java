package cn.edu.gzmu.service.helper;

import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.model.entity.SysRole;
import cn.edu.gzmu.model.entity.SysUser;
import cn.edu.gzmu.model.entity.Teacher;
import lombok.Data;
import org.springframework.util.Assert;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * 用户上下文对象，通过他来存取当前已经登录的用户的信息
 * 通过 <code>SecurityContextHolder.getContext().getAuthentication().getDetails()</code>
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 19-6-23 下午7:08
 */
@Data
@ParametersAreNonnullByDefault
public class UserContext {
    private SysUser sysUser;
    private List<SysRole> sysRoles;
    private Teacher teacher;
    private Student student;
    private boolean admin;

    /**
     * 检验用户是否拥有某个角色
     *
     * @param sysRole 用户角色
     * @return 结果
     */
    public boolean hasRole(SysRole sysRole) {
        Assert.notNull(sysRole, "The role can not be null!");
        Assert.notNull(sysRole.getName(), "The role name can not be null!");
        return hasRole(sysRole.getName());
    }

    /**
     * 检验用户是否拥有某个角色
     *
     * @param roleName 角色名
     * @return 结果
     */
    public boolean hasRole(String roleName) {
        return currentRoles().stream().map(SysRole::getName)
                .anyMatch(Predicate.isEqual(roleName));
    }

    /**
     * 获取用户当前角色
     *
     * @return 当前角色
     */
    public List<SysRole> currentRoles() {
        return sysRoles;
    }

    /**
     * 是否是管理员用户
     *
     * @return 结果
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * 获取当前用户
     *
     * @return 当前用户
     */
    public SysUser currentUser() {
        return sysUser;
    }

    /**
     * 存放实体
     *
     * @param teacher 教师实体
     */
    public void entity(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * 存放实体
     *
     * @param student 学生实体
     */
    public void entity(Student student) {
        this.student = student;
    }

    /**
     * 是否是学生角色
     *
     * @return 是否
     */
    public boolean isStudent() {
        return Objects.nonNull(student);
    }

    /**
     * 是否是教师角色
     *
     * @return 是否
     */
    public boolean isTeacher() {
        return Objects.nonNull(teacher);
    }

}
