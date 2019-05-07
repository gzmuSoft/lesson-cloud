package cn.edu.gzmu.generate.config;

import lombok.Data;
import lombok.experimental.Accessors;
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
@Accessors(chain = true)
@ConfigurationProperties(prefix = "gzmu.generate")
public class GenConfig {

    /**
     * 版本号
     */
    private String version;

    /**
     * 是否生成 service
     */
    private boolean genService = true;

    /**
     * 是否生成 controller
     */
    private boolean genController = true;

    /**
     * 是否生成 entity
     */
    private boolean genEntity = true;

    /**
     * 是否生成 repository
     */
    private boolean genRepository = true;

}
