package cn.edu.gzmu.auth.helper;

import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.model.entity.Teacher;
import cn.edu.gzmu.model.exception.AuthorizationServerException;
import cn.edu.gzmu.model.exception.ResourceException;
import cn.edu.gzmu.properties.Oauth2Properties;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @date 2019/8/1 上午11:20
 */
@Component
@AllArgsConstructor
public class OauthHelper {

    private final Oauth2Properties oauth2Properties;

    /**
     * 是否是匿名用户
     *
     * @return 结果
     */
    public static boolean isAnonymousUser() {
        final String anonymousUser = "anonymousUser";
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals(anonymousUser);
    }

    public Student student() {
        return userInfo("student", Student.class);
    }

    public Teacher teacher() {
        return userInfo("teacher", Teacher.class);
    }

    /**
     * 获取用户信息
     *
     * @param key   key
     * @param clazz class
     * @param <T>   class
     * @return entity
     */
    private <T> T userInfo(String key, Class<T> clazz) {
        ResponseEntity<JSONObject> response = accessTokenTemplate().exchange(oauth2Properties.getUserInfoUri(), HttpMethod.GET, null, JSONObject.class);
        if (!response.getStatusCode().is2xxSuccessful() || Objects.isNull(response.getBody())) {
            throw new AuthorizationServerException("请求资源服务器异常：{}", response.getStatusCode());
        }
        T entity = response.getBody().getObject(key, clazz);
        if (Objects.isNull(entity)) {
            throw new ResourceException("资源异常，找不到用户信息");
        }
        return entity;
    }

    /**
     * 生成访问 token 的时候用的 template
     *
     * @return RestTemplate
     */
    public RestTemplate accessTokenTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new BearerAuthenticationInterceptor());
        restTemplate.setInterceptors(interceptors);
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(
                oauth2Properties.getAuthorizationServerUrl()));
        return restTemplate;
    }

}
