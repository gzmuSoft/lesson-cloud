package cn.edu.gzmu.auth.handler;

import cn.edu.gzmu.model.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * <p>用户密码封装</p>
 *
 * @author echo
 * @version 1.0
 * @date 19-4-14 10:45
 */
@Component
@RepositoryEventHandler
public class SysUserEventHandler {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SysUserEventHandler(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 创建用户之前，对密码进行加密
     *
     * @param user 用户
     */
    @HandleBeforeSave
    public void handleSysUserSave(SysUser user) {
        if (user.getPwd() != null && !user.getPwd().startsWith("$2a$")) {
            user.setPwd(passwordEncoder.encode(user.getPwd()));
        }
    }

    @HandleBeforeCreate
    public void handleSysUserCreate(SysUser user) {
        user.setPwd(passwordEncoder.encode(user.getPwd()));
    }

}