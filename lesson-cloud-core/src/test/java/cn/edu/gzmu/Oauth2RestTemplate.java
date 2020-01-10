package cn.edu.gzmu;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/10 下午7:14
 */
public class Oauth2RestTemplate {

    private static final String AUTHORIZATION_SERVER_URL = "http://118.24.1.170:8888";
    private static final String LESSON_CLOUD_URL = "http://127.0.0.1:8080";
    private static final String CLIENT_ID = "lesson-cloud";
    private static final String CLIENT_SECRET = "lesson-cloud-secret";

    protected RestTemplate oauthRestTemplate;

    public Oauth2RestTemplate() {
        oauthRestTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new BearerAuthenticationInterceptor(accessToken()));
        oauthRestTemplate.setInterceptors(interceptors);
        oauthRestTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(LESSON_CLOUD_URL));
    }

    @Test
    void accessTokenWhenPassed() {
        System.out.println(accessToken());
    }


    private static class BearerAuthenticationInterceptor implements ClientHttpRequestInterceptor {

        private String accessToken;

        public BearerAuthenticationInterceptor(String accessToken) {
            this.accessToken = accessToken;
        }

        @NotNull
        @Override
        public ClientHttpResponse intercept(HttpRequest request, @NotNull byte[] body,
                                            @NotNull ClientHttpRequestExecution execution) throws IOException {
            HttpHeaders headers = request.getHeaders();
            if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
                headers.setBearerAuth(accessToken);
            }
            return execution.execute(request, body);
        }
    }

    private String accessToken(String username, String password, String scope) {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new BasicAuthenticationInterceptor(
                CLIENT_ID, CLIENT_SECRET
        ));
        restTemplate.setInterceptors(interceptors);
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(
                AUTHORIZATION_SERVER_URL));
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "password");
        form.add("username", username);
        form.add("password", password);
        form.add("scope", scope);
        ResponseEntity<JSONObject> response = restTemplate.postForEntity("/oauth/token", form, JSONObject.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        return response.getBody().getString("access_token");
    }

    private String accessToken(String username, String password) {
        return accessToken(username, password, "READ");
    }

    private String accessToken() {
        return accessToken("admin", "1997", "READ");
    }

}
