package ${package_name};

import ${base_entity};
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;


import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * ${table_name}
 *
 * @author echo
 * @version ${now_version}
 * @date ${.now?datetime}
 */
@Data
@Cacheable
@Table(name = "${table_name}")
@Entity(name = "${table_name}")
@Where(clause = "${where_clause}")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Cache(region = "${table_name}", usage = CacheConcurrencyStrategy.READ_WRITE)
public class ${class_name} extends BaseEntity implements Serializable {
<#list columns as column>

    /**
     * ${column.columnComment}
     */
    <#if !column.nullAble>
    @javax.validation.constraints.NotNull(message = "${column.columnName} 为必填项")
    </#if>
    <#if column.columnType == 'java.lang.String'>
    @Size(max = ${column.columnSize?c}, message = "${column.columnName} 不能大于 ${column.columnSize?c} 位")
    </#if>
    <#list column.otherConstraints as other>
    ${other}
    </#list>
    private ${column.columnType} ${column.columnName};
</#list>
}