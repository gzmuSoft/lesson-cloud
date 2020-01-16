package cn.edu.gzmu.integration.auth;

import cn.edu.gzmu.integration.ApplicationConfig;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.DefaultUriBuilderFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/15 下午3:37
 */
public class AuthorizationServerTest extends ApplicationConfig {

    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUp() {
        testRestTemplate = new TestRestTemplate();
        testRestTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(BASE_URL));
    }

    @Test
    @DisplayName("获取授权服务器登录地址 301")
    void authorizationServerUrlWhenPassed() {
        ResponseEntity<JSONObject> result = testRestTemplate.getForEntity("/auth/server", JSONObject.class);
        HttpHeaders headers = result.getHeaders();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.MOVED_PERMANENTLY);
        assertThat(headers.getLocation())
                .hasParameter("response_type")
                .hasParameter("client_id")
                .hasParameter("redirect_uri")
                .hasParameter("scope");
    }

    @Test
    @DisplayName("获取授权服务器登录地址 200")
    void authorizationServerUrl200WhenPassed() {
        ResponseEntity<JSONObject> result = testRestTemplate.getForEntity("/auth/serverUrl", JSONObject.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().containsKey("authorization_url"));
    }
}
