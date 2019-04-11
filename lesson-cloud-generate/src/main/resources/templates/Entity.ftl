package ${package_name};

import ${base_entity};
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;


import javax.persistence.Cacheable;
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
@Cacheable
@Table(name = "${table_name}")
@Entity(name = "${table_name}")
@Where(clause = "${where_clause}")
@EqualsAndHashCode(callSuper = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE )
public class ${class_name} extends BaseEntity {
<#list columns as column>

    /**
     * ${column.columnComment}
     */
    private ${column.columnType} ${column.columnName};
</#list>
}