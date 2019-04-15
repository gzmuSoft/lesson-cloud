package cn.edu.gzmu.social.qq.connect;

import cn.edu.gzmu.social.qq.api.Qq;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-15 20:40
 */
public class QqConnectionFactory extends OAuth2ConnectionFactory<Qq> {
    /**
     * Create a {@link OAuth2ConnectionFactory}.
     *
     * @param providerId the provider id e.g. "facebook"
     * @param appId      your app id
     * @param appSecret  your app secret
     */
    public QqConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QqServiceProvider(appId, appSecret), new QqAdapter());
    }
}
