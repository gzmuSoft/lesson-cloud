package cn.edu.gzmu.auth.helper;

import org.springframework.web.client.RestTemplate;

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
public class RestHelper {

    public static String accessToken = "";

    public static RestTemplate restTemplate = new RestTemplate();

}
