package cn.edu.gzmu.generate.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class GenDatabaseConfigUtilTest {

    @Autowired
    private GenDatabaseUtil genDatabaseUtil;

    @Test
    public void testGetTablesSuccess() {
        List<String> tables = genDatabaseUtil.getTables();
        assertNotNull(tables);
        log.info("Table number is: {}", tables.size());
        tables.forEach(log::info);
    }

    @Test
    public void testGetAllColumnsSuccess() {
        List<ColumnClass> allColumns = genDatabaseUtil.getColumns();
        assertNotNull(allColumns);
        log.info("All tables' columns number is {}", allColumns.size());
        allColumns.forEach(columnClass -> log.info(columnClass.toString()));
    }

    @Test
    public void testGetColumnsSuccess() {
        List<ColumnClass> columns = genDatabaseUtil.getColumns("sys_user");
        assertNotNull(columns);
        log.info("The sys_user columns number is {}", columns.size());
        columns.forEach(columnClass -> log.info(columnClass.toString()));
    }
}