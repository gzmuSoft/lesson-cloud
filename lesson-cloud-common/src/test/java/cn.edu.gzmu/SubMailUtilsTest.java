package cn.edu.gzmu;

import cn.edu.gzmu.util.SubMailUtils;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-14 16:23
 */
@SpringBootTest
public class SubMailUtilsTest {

    @Autowired
    private SubMailUtils subMailUtils;

    @Test
    public void application() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action", "登录");
        jsonObject.put("code", "123456");
        jsonObject.put("time", "123");
        subMailUtils.sendActionMessage("121", jsonObject);
    }
}
