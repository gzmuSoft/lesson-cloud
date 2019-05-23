package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.SysUser;
import cn.edu.gzmu.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;


/**
 * SysUser Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:05:31
 */
@RepositoryRestResource(path = "/sysUser")
public interface SysUserRepository extends BaseRepository<SysUser, Long> {

    /**
     * 通过名称查询用户，并非模糊查询。
     * 每次获取均需要通过非空判断。
     *
     * @param name 名称
     * @return 结果
     */
    @RestResource(path = "name", rel = "name", description = @Description("通过用户名称查询用户信息"))
    Optional<SysUser> findFirstByName(String name);

    /**
     * 通过手机号查询用户，并非模糊查询。
     * 每次获取均需要通过非空判断。
     *
     * @param phone 手机号
     * @return 结果
     */
    @RestResource(path = "phone", rel = "phone", description = @Description("通过手机号查询用户信息"))
    Optional<SysUser> findFirstByPhone(String phone);

    /**
     * 通过邮箱号查询用户，并非模糊查询。
     * 每次获取均需要通过非空判断。
     *
     * @param email 邮箱号
     * @return 结果
     */
    @RestResource(path = "email", rel = "email", description = @Description("通过邮箱号查询用户信息"))
    Optional<SysUser> findFirstByEmail(String email);

}