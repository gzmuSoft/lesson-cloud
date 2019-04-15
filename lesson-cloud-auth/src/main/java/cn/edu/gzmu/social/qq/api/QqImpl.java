package cn.edu.gzmu.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;


/**
 * @author echo
 * @version 1.0
 * @date 19-4-14 21:41
 */
public class QqImpl extends AbstractOAuth2ApiBinding implements Qq {
    /**
     * 获取 openid 地址
     */
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    /**
     * 获取用户信息地址
     */
    private static final String URL_GET_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    /**
     * 应用 id
     */
    private String appId;

    /**
     * openid
     */
    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QqImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String result = getRestTemplate().getForObject(String.format(URL_GET_OPENID, accessToken), String.class);
        System.out.println(result);
        this.openId = StringUtils.substringBetween(result, "\"openid\":", "}");

    }

    @Override
    public QqUserInfo getQQUserInfo()  {
        String url = String.format(URL_GET_USER_INFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        System.out.println(result);
        try {
            return objectMapper.readValue(result, QqUserInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("获取用户信息失败");
        }
    }
}
