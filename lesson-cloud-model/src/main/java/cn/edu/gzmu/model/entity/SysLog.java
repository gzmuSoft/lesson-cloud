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

/**
 * sys_log
 *
 * @author echo
 * @version 1.0
 * @date 2019-4-19 22:08:04
 */
@Data
@Cacheable
@Table(name = "sys_log")
@Entity(name = "sys_log")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Cache(region = "sys_log", usage = CacheConcurrencyStrategy.READ_WRITE )
public class SysLog extends BaseEntity {

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
    private java.lang.String fromUrl;

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