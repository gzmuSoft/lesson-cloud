package cn.edu.gzmu.util;

import cn.edu.gzmu.config.MessageConfig;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * sub mail 工具类
 *
 * @author echo
 * @version 1.0
 * @date 19-5-7 11:31
 */
@Slf4j
@Component
public class SubMailUtils {

    private final MessageConfig messageConfig;
    private final static HttpClient HTTP_CLIENT = HttpClients.createDefault();
    private static final String X_SEND = "https://api.mysubmail.com/message/xsend";
    private static final String MULTI_X_SEND = "https://api.mysubmail.com/message/multixsend";

    @Autowired
    public SubMailUtils(MessageConfig messageConfig) {
        this.messageConfig = messageConfig;
    }

    /**
     * 发送一条信息
     *
     * @param to     接收人
     * @param vars   模板变量
     */
    public boolean sendActionMessage(String to, JSONObject vars) {
        HttpPost httpPost = new HttpPost(X_SEND);
        JSONObject jsonParam = appInfo(messageConfig.getActionTemplate());
        jsonParam.put("to", to);
        jsonParam.put("vars", vars);
        httpPost.setEntity(entityBuilder(jsonParam.toJSONString()));
        HttpResponse resp;
        try {
            resp = HTTP_CLIENT.execute(httpPost);
            log.info(EntityUtils.toString(resp.getEntity(), "UTF-8"));
            return resp.getStatusLine().getStatusCode() == 200;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private JSONObject appInfo(String project) {
        JSONObject param = new JSONObject();
        param.put("appid", messageConfig.getAppId());
        param.put("signature", messageConfig.getAppKey());
        param.put("project", project);
        return param;
    }

    private StringEntity entityBuilder(String param) {
        StringEntity entity = new StringEntity(param, "UTF-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        return entity;
    }

    private JSONObject copyJson(JSONObject old) {
        JSONObject copy = new JSONObject();
        old.forEach(copy::put);
        return copy;
    }

}
