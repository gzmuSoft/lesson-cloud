package cn.edu.gzmu.service;

import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.model.entity.SysData;
import cn.edu.gzmu.model.entity.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;


/**
 * SysUser Service
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
public interface SysUserService extends BaseService<SysUser, Long> {
    /**
     * 通过用户名/手机号/邮箱查询用户
     *
     * @param user 用户名/手机号/邮箱
     * @return 用户
     */
    SysUser searchByAll(@NotNull String user);

    /**
     * 通过手机号查询用户
     *
     * @param phone 手机号
     * @return 用户
     */
    SysUser searchByPhone(@NotNull String phone);

    /**
     * 学生用户注册方法
     *
     * @param user    用户
     * @param student 学生
     * @param school  学校
     * @return 是否注册成功
     */
    SysUser register(@NotNull SysUser user, @NotNull Student student, @NotNull SysData school);

    /**
     * 分页查询所有学生完整信息
     *
     * @param pageable 分页
     * @return 结果
     */
    Page<SysUser> searchAll(Pageable pageable);
}
