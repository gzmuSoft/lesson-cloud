package cn.edu.gzmu.service;

import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.model.entity.SysData;
import cn.edu.gzmu.model.entity.SysUser;

import javax.validation.constraints.NotNull;


/**
 * SysUser Service
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
public interface SysUserService {

    /**
     * 学生用户注册方法
     *
     * @param user    用户
     * @param student 学生
     * @param school  学校
     * @return 是否注册成功
     */
    SysUser register(@NotNull SysUser user, @NotNull Student student, @NotNull SysData school);

}
