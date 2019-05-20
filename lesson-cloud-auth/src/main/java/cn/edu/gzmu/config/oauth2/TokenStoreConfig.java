package cn.edu.gzmu.config.oauth2;

import cn.edu.gzmu.properties.Oauth2Properties;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * token 配置
 *
 * @author echo
 * @version 1.0
 * @date 19-4-18 22:23
 */
@Configuration
public class TokenStoreConfig {

    private final RedissonConnectionFactory redissonConnectionFactory;

    public TokenStoreConfig(RedissonConnectionFactory redissonConnectionFactory) {
        this.redissonConnectionFactory = redissonConnectionFactory;
    }

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redissonConnectionFactory);
    }

}
