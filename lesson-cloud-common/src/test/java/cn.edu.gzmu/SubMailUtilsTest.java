package cn.edu.gzmu;

import cn.edu.gzmu.util.SubMailUtils;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-14 16:23
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SubMailUtilsTest {

    @Autowired
    private SubMailUtils subMailUtils;

    @Test
    public void application() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action", "登录");
        jsonObject.put("code", "123456");
        jsonObject.put("time", "123");
        boolean b = subMailUtils.sendActionMessage("121", jsonObject);
        assertTrue(b);
    }
}
