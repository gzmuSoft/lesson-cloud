package cn.edu.gzmu.generate.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 * @author echo
 */
@Data
@AllArgsConstructor()
@NoArgsConstructor
public class ColumnClass {
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 列名称
     */
    private String columnName;
    /**
     * 列大小
     */
    private Integer columnSize;
    /**
     * 列的类型
     */
    private String columnType;
    /**
     * 列的注释
     */
    private String columnComment;
    /**
     * 是否能为空值
     */
    private Boolean nullAble;
    /**
     * 其他约束
     */
    private List<String> otherConstraints;
}
