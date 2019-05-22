package cn.edu.gzmu;

import cn.edu.gzmu.model.annoection.VerifyParameter;
import cn.edu.gzmu.model.entity.*;
import cn.edu.gzmu.service.SysResService;
import cn.edu.gzmu.service.SysRoleService;
import cn.edu.gzmu.service.SysUserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

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
        JSONObject me = new JSONObject();
        List<SysRole> roles = sysRoleService.searchByUsername(userDetails.getUsername());
        me.put("user", userDetails);
        me.put("roles", roles);
        List<SysRes> res = sysResService.searchByRoles(roles);
        me.put("res", res);
        return ResponseEntity.ok(me);
    }

    @PostMapping("/register")
    @VerifyParameter(
            required = {"user.name", "student.id", "student.name",
                    "user.email", "user.phone", "school.id"
            },
            equal = {"school.type|1"}
    )
    public HttpEntity<?> register(@NotNull @RequestBody JSONObject params) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                sysUserService.register(params.getObject("user", SysUser.class),
                        params.getObject("student", Student.class),
                        params.getObject("school", SysData.class))
        );
    }

    @PostMapping("/test")
    @VerifyParameter(
            required = {"user.name", "student.id", "student.name",
                    "user.email", "user.phone", "school.id"
            },
            equal = {"school.type|1"}
    )
    public HttpEntity<?> registerTest(@NotNull @RequestBody JSONObject params) {
        return ResponseEntity.ok().build();
    }
}
