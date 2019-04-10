package ${package_name};

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ${table_name}
 *
 * @author echo
 * @version ${now_version}
 * @date ${.now?datetime}
 */
@Data
@Entity
@Table(name = "${table_name}")
@EqualsAndHashCode(callSuper = true)
public class ${class_name} extends ${base_entity} {
<#list columns as column>

    /**
     * ${column.columnComment}
     */
    private ${column.columnType} ${column.columnName};
</#list>
}