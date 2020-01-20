package cn.edu.gzmu.service.helper;

import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.model.entity.Teacher;
import cn.edu.gzmu.repository.auth.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author echo
 * @date 2019/8/1 上午11:20
 */
@Component
@AllArgsConstructor
public class OauthHelper {

    private final UserRepository userRepository;

    /**
     * 是否是匿名用户
     *
     * @return 结果
     */
    public static boolean isAnonymousUser() {
        final String anonymousUser = "anonymousUser";
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals(anonymousUser);
    }

    /**
     * 获取当前登录的学生.
     *
     * @return 结果
     */
    public Student student() {
        return userRepository.me().getObject("student", Student.class);
    }

    /**
     * 获取当前登录的教师.
     *
     * @return 结果
     */
    public Teacher teacher() {
        return userRepository.me().getObject("teacher", Teacher.class);
    }

}
