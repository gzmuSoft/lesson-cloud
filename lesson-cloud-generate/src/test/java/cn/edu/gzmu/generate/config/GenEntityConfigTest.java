package cn.edu.gzmu.generate.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-9 21:08
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GenEntityConfigTest {

    private @Autowired GenEntityConfig genEntityConfig;

    @Test
    public void getConfig() {
        assertNotNull(genEntityConfig.getBaseEntity());
        assertNotNull(genEntityConfig.getPackageName());
    }
}
