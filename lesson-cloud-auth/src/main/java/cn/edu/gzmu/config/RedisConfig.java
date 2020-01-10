package cn.edu.gzmu.config;

import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * 缓存配置类(读取model模块的yaml配置文件)
 *
 * @author echo
 * @version 1.0
 * @date 19-4-12 09:50
 */
@Configuration
@AllArgsConstructor
public class RedisConfig {

    private final RedisConnectionFactory redisConnectionFactory;

    @Bean
    public CacheManager cacheManager() {
        return RedisCacheManager.create(redisConnectionFactory);
    }

}
