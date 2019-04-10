package cn.edu.gzmu.generate.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 生成配置
 *
 * @author echo
 * @version 1.0
 * @date 19-4-9 21:05
 */
@Data
@Component
@ConfigurationProperties(prefix = "gzmu.generate")
public class GenConfig {
    private String version;
}
