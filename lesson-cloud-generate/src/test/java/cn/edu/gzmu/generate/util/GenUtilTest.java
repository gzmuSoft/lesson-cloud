package cn.edu.gzmu.generate.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-9 22:32
 */
@Slf4j
public class GenUtilTest {

    @Test
    public void testGetParentPath() {
        log.info(GenUtil.getParentPath());
    }

    @Test
    public void testDirPathContact() {
        log.info(GenUtil.dirPathContact("src", "main"));
    }

    @Test
    public void testUnderlineToHump() {
        log.info(GenUtil.underlineToHump("sys_user"));
        log.info(GenUtil.underlineToHump("sys_user", true));
        System.out.println(GenUtil.toPlural("exam_history"));
    }
}