package cn.edu.gzmu;

import cn.edu.gzmu.properties.Oauth2Properties;
import cn.edu.gzmu.repository.auth.Oauth2Repository;
import cn.edu.gzmu.repository.auth.UserRepository;
import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

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

    private final @NonNull Oauth2Properties oauth2Properties;
    private final @NonNull Oauth2Repository oauth2Repository;
    private final @NonNull UserRepository userRepository;

    /**
     * 获取授权服务器的登录地址
     * 200 请求头.
     *
     * @return http
     * @throws URISyntaxException 配置解析异常
     */
    @GetMapping("/serverUrl")
    public HttpEntity<?> authServerUrl() throws URISyntaxException {
        JSONObject result = new JSONObject();
        result.put("authorization_url", authServerUrlBuilder());
        return ResponseEntity.ok(result);
    }

    /**
     * 获取授权服务器的登录地址
     * 301 请求头.
     *
     * @return http
     * @throws URISyntaxException 配置解析异常
     */
    @GetMapping("/server")
    public HttpEntity<?> authServer() throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, authServerUrlBuilder());
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    /**
     * 授权码模式获取 token
     *
     * @param code 授权码
     * @return 结果
     */
    @PostMapping("/code")
    public HttpEntity<?> authCode(@RequestParam String code) {
        Map<String, String> form = new HashMap<>(4);
        form.put("grant_type", "authorization_code");
        form.put("code", code);
        form.put("redirect_uri", oauth2Properties.getRedirectUrl());
        form.put("scope", oauth2Properties.getScope());
        return ResponseEntity.ok(oauth2Repository.postAccessToken(form));
    }

    /**
     * 刷新 token.
     *
     * @param token refresh token
     * @return 结果
     */
    @PostMapping("/refresh")
    public HttpEntity<?> refreshToken(@RequestParam String token) {
        Map<String, String> form = new HashMap<>(4);
        form.put("grant_type", "refresh_token");
        form.put("refresh_token", token);
        return ResponseEntity.ok(oauth2Repository.postAccessToken(form));
    }

    /**
     * 检查 token.
     *
     * @param token access token
     * @return 结果
     */
    @PostMapping("/check")
    public HttpEntity<?> checkToken(@RequestParam String token) {
        return ResponseEntity.ok(oauth2Repository.checkToken(token));
    }

    /**
     * 退出登录地址.
     *
     * @return 结果
     * @throws URISyntaxException url 构建异常
     */
    @GetMapping("/logout")
    public HttpEntity<?> logout(@RequestParam(required = false, defaultValue = "false") Boolean security)
            throws URISyntaxException {
        URIBuilder logoutUri = new URIBuilder(
                oauth2Properties.getAuthorizationServerUrl() + oauth2Properties.getLogoutUri());
        logoutUri.addParameter("redirect_url", oauth2Properties.getLogoutRedirectUrl());
        if (security) {
            logoutUri.addParameter("client_id", oauth2Properties.getClientId());
        }
        JSONObject result = new JSONObject();
        result.put("logout_url", logoutUri.build().toString());
        return ResponseEntity.ok(result);
    }

    /**
     * 获取用户信息.
     *
     * @return 结果
     */
    @GetMapping("/me")
    public HttpEntity<?> me() {
        return ResponseEntity.ok(userRepository.me());
    }

    /**
     * 构建授权服务器登录地址.
     *
     * @return 结果
     * @throws URISyntaxException uri 异常
     */
    private String authServerUrlBuilder() throws URISyntaxException {
        URIBuilder authorizeUri = new URIBuilder(
                oauth2Properties.getAuthorizationServerUrl() + oauth2Properties.getAuthorizeUri());
        authorizeUri
                .addParameter("response_type", "code")
                .addParameter("client_id", oauth2Properties.getClientId())
                .addParameter("redirect_uri", oauth2Properties.getRedirectUrl())
                .addParameter("scope", oauth2Properties.getScope());
        return authorizeUri.build().toString();
    }
}
