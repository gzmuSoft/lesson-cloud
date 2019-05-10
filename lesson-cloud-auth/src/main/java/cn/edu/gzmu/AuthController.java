package cn.edu.gzmu;

import cn.edu.gzmu.model.entity.SysUser;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody SysUser user) {

        return ResponseEntity.ok().build();
    }
}
