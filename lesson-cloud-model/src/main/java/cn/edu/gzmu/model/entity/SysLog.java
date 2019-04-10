package cn.edu.gzmu.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * sys_log
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-10 14:11:01
 */
@Data
@Entity
@Table(name = "sys_log")
@EqualsAndHashCode(callSuper = true)
public class SysLog extends cn.edu.gzmu.model.BaseEntity {

    /**
     * 浏览器
     */
    private java.lang.String browser;

    /**
     * 操作方式：GET/POST
     */
    private java.lang.String operation;

    /**
     * 访问的实际url地址
     */
    private java.lang.String from;

    /**
     * 来源ip地址
     */
    private java.lang.String ip;

    /**
     * 访问url相对地址
     */
    private java.lang.String url;

    /**
     * 1-记录
     */
    private java.lang.String status;
}