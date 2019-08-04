package cn.edu.gzmu.auth.helper;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2019/8/2 下午6:05
 */
@RequiredArgsConstructor
public class BearerAuthenticationInterceptor implements ClientHttpRequestInterceptor {

    private final String accessToken;

    @NotNull
    @Override
    public ClientHttpResponse intercept(@NotNull HttpRequest request,
                                        @NotNull byte[] body, @NotNull ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
            headers.setBearerAuth(accessToken);
        }
        return execution.execute(request, body);
    }
}
