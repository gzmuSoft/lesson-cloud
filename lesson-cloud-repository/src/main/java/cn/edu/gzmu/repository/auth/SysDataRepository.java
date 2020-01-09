package cn.edu.gzmu.repository.auth;

import cn.edu.gzmu.model.entity.SysData;
import cn.edu.gzmu.repository.interceptor.BearerRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * SysData Repository
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @version 1.0
 * @date 2019-5-23 17:38:13
 */
@FeignClient(name = "gzmu-auth", configuration = BearerRequestInterceptor.class,
        url = "http://118.24.1.170:8888")
public interface SysDataRepository {

    /**
     * 根据 id 查询
     *
     * @param id  结果
     * @return 结果
     */
    @GetMapping("/api/sysData/{id}")
    SysData findById(@PathVariable("id") Long id);

}