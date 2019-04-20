package cn.edu.gzmu.social.qq.config;

import cn.edu.gzmu.properties.QqProperties;
import cn.edu.gzmu.social.qq.connect.QqConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-15 20:53
 */
//@Configuration
@ConditionalOnProperty(prefix = "gzmu.social.qq", name = "app-id")
public class QqAutoConfig extends SocialConfigurerAdapter {

    @Autowired
    private QqProperties qq;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment environment) {
        configurer.addConnectionFactory(createConnectionFactory());
    }

    public ConnectionFactory<?> createConnectionFactory() {
        return new QqConnectionFactory(qq.getProviderId(), qq.getAppId(), qq.getAppSecret());
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return null;
    }
}
