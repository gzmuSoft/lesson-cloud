package cn.edu.gzmu.generate.util;

import cn.edu.gzmu.generate.config.GenDatabaseConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库工具类
 *
 * @author echo
 * @version 1.0
 * @date 19-4-9 21:05
 */
@Slf4j
@Component
public class GenDatabaseUtil {
    private final GenDatabaseConfig genDatabaseConfig;

    public GenDatabaseUtil(GenDatabaseConfig genDatabaseConfig) {
        this.genDatabaseConfig = genDatabaseConfig;
    }

    /**
     * 获取数据库元数据
     *
     * @return 元数据
     * @throws Exception 异常
     */
    private DatabaseMetaData getMetaData() throws Exception {
        Class.forName(genDatabaseConfig.getDriverClassName());
        return DriverManager.getConnection(genDatabaseConfig.getUrl(),
                genDatabaseConfig.getUsername(), genDatabaseConfig.getPassword()).getMetaData();
    }

    /**
     * 获取库的所有表
     *
     * @return 所有表
     */
    public List<String> getTables() {
        List<String> tables = new ArrayList<>();
        try {
            ResultSet resultSet = getMetaData().getTables(genDatabaseConfig.getCatalog(), null,
                    genDatabaseConfig.getPrefix() + "%", new String[]{"TABLE"});
            while (resultSet.next()) {
                tables.add(resultSet.getString("TABLE_NAME"));
            }
            resultSet.close();
        } catch (Exception e) {
            log.error("Please check your database conf! {}", e.getMessage());
            e.printStackTrace();
        }
        return tables;
    }

    /**
     * 获取库的所有列
     *
     * @return 所有列
     */
    public List<ColumnClass> getColumns() {
        List<ColumnClass> columns = new ArrayList<>();
        for (String table : getTables()) {
            try (ResultSet resultSet = getMetaData().getColumns(genDatabaseConfig.getCatalog(), null, table, "%")) {
                columns.addAll(getColumns(resultSet, table, false));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return columns.size() > 0 ? columns : null;
    }

    /**
     * 获取指定表的所有列
     *
     * @param tableName 表名
     * @return 所有列的集合
     */
    public List<ColumnClass> getColumns(String tableName) {
        try (ResultSet resultSet = getMetaData().getColumns(genDatabaseConfig.getCatalog(), null, tableName, "%")) {
            return getColumns(resultSet, tableName, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取某列的结果集抽取
     *
     * @param resultSet 结果集
     * @param tableName 表名
     * @param exclude 是否排除公共字段
     * @throws SQLException 异常
     */
    private List<ColumnClass> getColumns(ResultSet resultSet, String tableName, boolean exclude) throws SQLException {
        List<ColumnClass> columns = new ArrayList<>();
        while (resultSet.next()) {
            String columnName = resultSet.getString("COLUMN_NAME");
            if (exclude && hasBaseField(columnName)) {
                continue;
            }
            columns.add(new ColumnClass(
                    tableName,
                    GenUtil.underlineToHump(columnName),
                    GenUtil.fieldConversion(resultSet.getString("TYPE_NAME")),
                    resultSet.getString("REMARKS")
            ));
        }
        return columns;
    }


    /**
     * 是否拥有公共字段
     *
     * @param field 公共字段
     * @return 结果
     */
    private boolean hasBaseField(String field) {
        return genDatabaseConfig.getBaseField().contains(field);
    }


}
