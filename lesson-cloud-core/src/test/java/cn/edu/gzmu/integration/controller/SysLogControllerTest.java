package cn.edu.gzmu.integration.controller;

import cn.edu.gzmu.integration.Oauth2RestTemplate;
import cn.edu.gzmu.model.entity.SysLog;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

/**
 * .
 *
 *
 * @date 2020/1/10 下午7:40
 */
public class SysLogControllerTest extends Oauth2RestTemplate {

    @Test
    void getAllSysLogWhenPassed() {
        ResponseEntity<JSONObject> sysLogs = oauthRestTemplate.getForEntity("/sysLog", JSONObject.class);
        assertEquals(HttpStatus.OK, sysLogs.getStatusCode());
        assertNotNull(sysLogs.getBody());
        assertTrue(sysLogs.getBody().containsKey("_embedded"));
    }

    @Test
    void addOneSysLogWhenPassed() {
        SysLog sysLog = new SysLog();
        sysLog.setArgs("test")
                .setBrowser("Test")
                .setFromUrl("test")
                .setIp("test")
                .setOperation("test")
                .setResult("test")
                .setStatus("1")
                .setUrl("test");
        ResponseEntity<JSONObject> response = oauthRestTemplate.postForEntity("/sysLog", sysLog, JSONObject.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

}
