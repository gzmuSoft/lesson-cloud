package cn.edu.gzmu.generate.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.junit.Assert.*;
/**
 * @author echo
 * @version 1.0
 * @date 19-4-9 21:19
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GenConfigTest {

    private @Autowired GenConfig genConfig;

    @Test
    public void getConfig() {
        assertNotNull(genConfig.getVersion());
        System.out.println(genConfig.getVersion());
    }

}
