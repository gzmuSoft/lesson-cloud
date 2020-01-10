package cn.edu.gzmu;

import cn.edu.gzmu.util.RandomCode;
import org.junit.jupiter.api.Test;

public class RandomCodeTest {
    @Test
    public void randomTest() {
        for (int i = 0; i < 20; i++) {
            System.out.println(RandomCode.random(4, true));
        }
    }
}
