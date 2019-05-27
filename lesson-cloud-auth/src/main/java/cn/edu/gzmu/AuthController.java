package cn.edu.gzmu;

import cn.edu.gzmu.model.annoection.VerifyParameter;
import cn.edu.gzmu.model.entity.*;
import cn.edu.gzmu.service.SysResService;
import cn.edu.gzmu.service.SysRoleService;
import cn.edu.gzmu.service.SysUserService;
import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class AuthController {

    private final @NonNull SysRoleService sysRoleService;
    private final @NonNull SysResService sysResService;
    private final @NonNull SysUserService sysUserService;

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
            required = {
                    "user.name#用户名称不能为空！",
                    "student.id#学生id为必填项！",
                    "student.name#学生名称为必填项！",
                    "user.email#用户邮箱为必填项！",
                    "user.phone#用户手机号为必填项！",
                    "school.id#学校为必填项！"
            },
            equal = {"school.type|1#选择的数据类型必须为学校类型！"}
    )
    public HttpEntity<?> register(@NotNull @RequestBody JSONObject params) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                sysUserService.register(
                        params.getObject("user", SysUser.class),
                        params.getObject("student", Student.class),
                        params.getObject("school", SysData.class)
                )
        );
    }

}
