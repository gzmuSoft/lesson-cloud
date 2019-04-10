package cn.edu.gzmu.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * sys_data
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-10 14:11:00
 */
@Data
@Entity
@Table(name = "sys_data")
@EqualsAndHashCode(callSuper = true)
public class SysData extends cn.edu.gzmu.model.BaseEntity {

    /**
     * 0，代表无上级，即：学校
     */
    private java.lang.Long parentId;

    /**
     * 简介
     */
    private java.lang.String brief;

    /**
     * 0：学校，1：学院，2：系部，3：专业，4：班级
     */
    private java.lang.Integer type;
}