package cn.edu.gzmu.generate.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 实体类生成配置
 *
 * @author echo
 * @version 1.0
 * @date 19-4-9 21:05
 */
@Data
@Component
@ConfigurationProperties(prefix = "gzmu.generate.repository")
public class GenRepositoryConfig {

    /**
     * 包名
     */
    private String packageName;

    /**
     * 实体类路径
     */
    private String baseRepository;

    /**
     * 模块名
     */
    private String moduleName;
}
