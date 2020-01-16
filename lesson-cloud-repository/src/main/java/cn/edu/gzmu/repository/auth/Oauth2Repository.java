package cn.edu.gzmu.repository.auth;

import cn.edu.gzmu.repository.interceptor.BearerRequestInterceptor;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/15 下午10:56
 */
@FeignClient(name = "oauth2", configuration = BearerRequestInterceptor.class,
        url = "http://118.24.1.170:8888")
public interface Oauth2Repository {

    /**
     * 请求 token
     *
     * @param parameters 参数
     * @return 结果
     */
    @PostMapping("/oauth/token")
    JSONObject postAccessToken(@RequestParam Map<String, String> parameters);

    /**
     * 检查 token
     *
     * @param value 参数
     * @return 结果
     */
    @RequestMapping(value = "/oauth/check_token")
    JSONObject checkToken(@RequestParam("token") String value);
}
