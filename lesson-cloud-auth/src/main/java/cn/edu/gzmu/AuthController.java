package cn.edu.gzmu;

import cn.edu.gzmu.model.entity.SysRes;
import cn.edu.gzmu.model.entity.SysRole;
import cn.edu.gzmu.model.entity.SysUser;
import cn.edu.gzmu.service.SysResService;
import cn.edu.gzmu.service.SysRoleService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    public AuthController(SysRoleService sysRoleService, SysResService sysResService) {
        this.sysRoleService = sysRoleService;
        this.sysResService = sysResService;
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
    public HttpEntity<?> register(@RequestBody SysUser user) {

        return ResponseEntity.ok().build();
    }
}
