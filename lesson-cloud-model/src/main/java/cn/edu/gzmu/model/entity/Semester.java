package cn.edu.gzmu.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * semester
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-10 14:11:00
 */
@Data
@Entity
@Table(name = "semester")
@EqualsAndHashCode(callSuper = true)
public class Semester extends cn.edu.gzmu.model.BaseEntity {

    /**
     * 学校编号
     */
    private java.lang.Long schoolId;

    /**
     * 起始日期
     */
    private java.time.LocalDate startDate;

    /**
     * 结束日期
     */
    private java.time.LocalDate endDate;
}