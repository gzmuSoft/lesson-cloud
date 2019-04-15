package cn.edu.gzmu.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-15 20:50
 */
@Data
@Component("qq")
@ConfigurationProperties("gzmu.social.qq")
public class QqProperties {
    private String providerId = "qq";
    private String appId;
    private String appSecret;
}
