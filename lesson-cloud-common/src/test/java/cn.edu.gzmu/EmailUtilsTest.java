package cn.edu.gzmu;

import cn.edu.gzmu.util.EmailUtils;
import cn.edu.gzmu.util.RandomCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @author Japoul
 * @version 1.0
 * @date 2019-05-21 16:45
 */
@SpringBootTest
public class EmailUtilsTest {

    @Autowired
    private EmailUtils emailUtils;

    @Test
    public void application() {
        HashMap<String, Object> values = new HashMap<>();
        values.put("code", RandomCode.random(6, false));
        values.put("time", 10);
        String toEmail = "lizhongyue248@163.com";
        String type = "注册";
        String subject = "云课程注册邮件";
        String template = "registerTemplate.html";
        Future<String> res = emailUtils.sendTemplateMail(toEmail, type, subject, template, values);
        assertTrue(res.isDone());
    }
}
