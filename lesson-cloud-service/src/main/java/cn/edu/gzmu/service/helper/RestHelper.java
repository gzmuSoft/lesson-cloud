package cn.edu.gzmu.service.helper;

import cn.edu.gzmu.model.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 资源服务器请求帮助类
 * <p>
 * 当需要向授权服务器请求需要认证的资源时
 * 请使用 {@code RestHelper.restTemplate}
 * <p>
 * 例如：
 * get 请求某个资源路径
 * <code>
 * RestHelper.restTemplate.getForEntity("/url", Map.class)
 * </code>
 * <p>
 * 获取当前登录用户的 {@code access_token}
 * <code>
 * RestHelper.accessToken
 * </code>
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2019/8/2 下午5:58
 */
@Slf4j
public class RestHelper {

    public static String accessToken = "";

    public static RestTemplate restTemplate = new RestTemplate();

    public static <S extends BaseEntity> S getByIdForEntity(Long id, Function<Long, String> repository, Class<S> sClass) {
        if (Objects.isNull(id)) {
            return null;
        }
        String url = repository.apply(id);
        log.debug("Request for auth server:" + url + " and the return: " + sClass.getName());
        ResponseEntity<S> response = restTemplate.getForEntity(url, sClass);
        log.debug("Response from auth server:" + response.getStatusCodeValue());
        log.debug("Response from auth server:" + response.getHeaders());
        if (!response.getStatusCode().is2xxSuccessful()) {
            return null;
        }
        return response.getBody();
    }

    @SuppressWarnings("all")
    public static <S extends BaseEntity> List<S> getByIdsForEntity(String ids, Supplier<String> repository, Class<S> sClass) {
        if (Objects.isNull(ids)) {
            return null;
        }
        String url = repository.get() + "?ids=" + ids;
        log.debug("Request for auth server:" + url + " and the return: " + sClass.getName());
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        log.debug("Response from auth server:" + response.getStatusCodeValue());
        log.debug("Response from auth server:" + response.getHeaders());
        if (!response.getStatusCode().is2xxSuccessful()) {
            return null;
        }
        return response.getBody();
    }

}
