package cn.edu.gzmu.social.qq.connect;

import cn.edu.gzmu.social.qq.api.Qq;
import cn.edu.gzmu.social.qq.api.QqImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-14 21:56
 */
public class QqServiceProvider extends AbstractOAuth2ServiceProvider<Qq> {

    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    private String appId;

    public QqServiceProvider(String appId, String appSecret) {
        super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
    }

    @Override
    public Qq getApi(String accessToken) {
        return new QqImpl(accessToken, appId);
    }
}
