package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;


import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * sel_options
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-20 0:08:37
 */
@Data
@Cacheable
@Table(name = "sel_options")
@Entity(name = "sel_options")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Cache(region = "sel_options", usage = CacheConcurrencyStrategy.READ_WRITE )
public class SelOptions extends BaseEntity implements Serializable {

    /**
     * 题目编号
     */
    private java.lang.Long questionId;

    /**
     * 题目类型，0：单项选择题，1：多项选择题，2：填空题
     */
    private java.lang.Short type;
}