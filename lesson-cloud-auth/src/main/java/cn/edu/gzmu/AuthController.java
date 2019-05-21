package cn.edu.gzmu;

import cn.edu.gzmu.model.constant.SysDataEnum;
import cn.edu.gzmu.model.entity.*;
import cn.edu.gzmu.service.SysResService;
import cn.edu.gzmu.service.SysRoleService;
import cn.edu.gzmu.service.SysUserService;
import com.alibaba.fastjson.JSONObject;
import org.checkerframework.checker.units.qual.A;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 授权信息
 *
 * @author echo
 * @version 1.0
 * @date 19-4-16 20:46
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final SysRoleService sysRoleService;
    private final SysResService sysResService;
    private final SysUserService sysUserService;

    public AuthController(SysRoleService sysRoleService, SysResService sysResService,
                          SysUserService sysUserService) {
        this.sysRoleService = sysRoleService;
        this.sysResService = sysResService;
        this.sysUserService = sysUserService;
    }

    @GetMapping("/me")
    public HttpEntity<?> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        Map<String, Object> me = new ConcurrentHashMap<>(3);
        List<SysRole> roles = sysRoleService.searchByUsername(userDetails.getUsername());
        me.put("user", userDetails);
        me.put("roles", roles);
        List<SysRes> res = sysResService.searchByRoles(roles);
        me.put("res", res);
        return ResponseEntity.ok(me);
    }

    @PostMapping("/register")
    public HttpEntity<?> register(@NotNull @RequestBody JSONObject params) {
        SysUser user = params.getObject("user", SysUser.class);
        Student student = params.getObject("student", Student.class);
        SysData school = params.getObject("school", SysData.class);
        Assert.notNull(user, "请求中不存在用户信息");
        Assert.notNull(student, "请求中不存在学生信息");
        Assert.notNull(student.getId(), "找不到对应的学生信息");
        Assert.notNull(school, "请求中不存在学校信息");
        Assert.notNull(school.getId(), "找不到对应的学校信息");
        Assert.notNull(school.getType(), "请求中不存在学校类型！");
        Assert.isTrue(SysDataEnum.match(SysDataEnum.SCHOOL, school.getType()), "并非学校类型");
        return ResponseEntity.status(HttpStatus.CREATED).body(sysUserService.register(user, student, school));
    }
}
