package cn.edu.gzmu.generate.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author echo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableClass {
    private String tableName;
    private String className;
    private String classNameNew;
}
