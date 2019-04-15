package cn.edu.gzmu.social.qq.connect;

import cn.edu.gzmu.social.qq.api.Qq;
import cn.edu.gzmu.social.qq.api.QqUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-15 20:35
 */
public class QqAdapter implements ApiAdapter<Qq> {


    @Override
    public boolean test(Qq api) {
        return true;
    }

    @Override
    public void setConnectionValues(Qq api, ConnectionValues values) {
        QqUserInfo userInfo = api.getQQUserInfo();
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        values.setProfileUrl(null);
        values.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(Qq api) {
        return null;
    }

    @Override
    public void updateStatus(Qq api, String message) {
        // nothing to do
    }
}
