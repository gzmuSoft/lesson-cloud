package cn.edu.gzmu;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-16 20:46
 */
@RestController()
public class UserController {

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }
}
