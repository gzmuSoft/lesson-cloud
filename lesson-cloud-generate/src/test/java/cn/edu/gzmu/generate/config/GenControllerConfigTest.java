package cn.edu.gzmu.generate.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-9 21:08
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GenControllerConfigTest {

    private @Autowired GenControllerConfig genControllerConfig;

    @Test
    public void getConfig() {
        assertNotNull(genControllerConfig.getBaseController());
        assertNotNull(genControllerConfig.getPackageName());
    }
}
