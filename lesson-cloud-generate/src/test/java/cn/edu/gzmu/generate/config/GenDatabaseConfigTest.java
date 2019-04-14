package cn.edu.gzmu.generate.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GenDatabaseConfigTest {


    private @Autowired GenDatabaseConfig genDatabaseConfig;


    @Test
    public void getConfig() {
        assertAllNotNull(
                genDatabaseConfig, genDatabaseConfig.getDriverClassName(), genDatabaseConfig.getUsername(),
                genDatabaseConfig.getUrl(), genDatabaseConfig.getPassword(), genDatabaseConfig.getPrefix(),
                genDatabaseConfig.getBaseField()
        );
    }

    private void assertAllNotNull(Object... args) {
        for (Object arg : args) {
            assertNotNull(arg);
        }
    }
}
