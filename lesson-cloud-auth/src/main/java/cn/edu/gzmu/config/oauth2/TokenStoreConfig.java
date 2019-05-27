package cn.edu.gzmu.config.oauth2;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * token 配置
 *
 * @author echo
 * @version 1.0
 * @date 19-4-18 22:23
 */
@Configuration
@RequiredArgsConstructor
public class TokenStoreConfig {

    private final @NonNull RedissonConnectionFactory redissonConnectionFactory;

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redissonConnectionFactory);
    }

}
