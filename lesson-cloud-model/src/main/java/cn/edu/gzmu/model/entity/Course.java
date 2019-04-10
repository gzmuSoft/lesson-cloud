package cn.edu.gzmu.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * course
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-10 14:10:57
 */
@Data
@Entity
@Table(name = "course")
@EqualsAndHashCode(callSuper = true)
public class Course extends cn.edu.gzmu.model.BaseEntity {

    /**
     * 基础学时
     */
    private java.lang.Short period;

    /**
     * 基础学分
     */
    private java.lang.Float credit;

    /**
     * 课程性质
     */
    private java.lang.String type;
}