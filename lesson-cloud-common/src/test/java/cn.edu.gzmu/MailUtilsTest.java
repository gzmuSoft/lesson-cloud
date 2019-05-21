package cn.edu.gzmu;

import cn.edu.gzmu.util.MailUtils;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

/**
 * @author Japoul
 * @version 1.0
 * @date 2019-05-21 16:45
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MailUtilsTest {

    @Autowired
    private MailUtils mailUtils;

    @Test
    public void application() {
        HashMap<String, String> values = new HashMap<String, String>();
        values.put("code", "hvuiwhiu%%D7bfhb7fasdbgjh");
        values.put("user", "Echo");
        String toEmail = "lizhongyue248@163.com";
        String type = "注册";
        String subject = "云课程注册邮件";
        String template = "registerTemplate.html";
        boolean res = mailUtils.sendTemplateMail(toEmail, type, subject, template, values);
        assertTrue(res);
    }
}
