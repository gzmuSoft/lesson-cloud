package cn.edu.gzmu.repository.auth;

import cn.edu.gzmu.repository.interceptor.BearerRequestInterceptor;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/16 下午9:38
 */
@FeignClient(name = "user", configuration = BearerRequestInterceptor.class,
        url = "http://118.24.1.170:8888")
public interface UserRepository {

    /**
     * 获取登录用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/auth/me")
    JSONObject me();
}
