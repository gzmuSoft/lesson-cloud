package cn.edu.gzmu.validate.impl;

import cn.edu.gzmu.constant.ValidateCodeType;
import cn.edu.gzmu.validate.ValidateCode;
import cn.edu.gzmu.validate.ValidateCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-16 22:18
 */
public abstract class AbstractValidateCodeRepository implements ValidateCodeRepository {

    @Autowired
    private RedisTemplate<String, ValidateCode> redisTemplate;

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type) {
        redisTemplate.opsForValue().set(buildKey(request, type), code, 60, TimeUnit.SECONDS);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
        return redisTemplate.opsForValue().get(buildKey(request, type));
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType type) {
        redisTemplate.delete(buildKey(request, type));
    }

    protected abstract String buildKey(ServletWebRequest request, ValidateCodeType type);
}
