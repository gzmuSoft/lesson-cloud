package cn.edu.gzmu.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * sel_options
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-10 14:11:00
 */
@Data
@Entity
@Table(name = "sel_options")
@EqualsAndHashCode(callSuper = true)
public class SelOptions extends cn.edu.gzmu.model.BaseEntity {

    /**
     * 题目编号
     */
    private java.lang.Long questionId;

    /**
     * 题目类型，0：单项选择题，1：多项选择题，2：填空题
     */
    private java.lang.Short type;
}