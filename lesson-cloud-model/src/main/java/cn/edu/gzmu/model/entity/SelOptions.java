package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * sel_options
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-27 10:59:08
 */
@Data
@Cacheable
@ToString(callSuper = true)
@Table(name = "sel_options")
@Entity(name = "sel_options")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Cache(region = "sel_options", usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class SelOptions extends BaseEntity implements Serializable {

    /**
     * 题目编号
     */
    @javax.validation.constraints.NotNull(message = "questionId 题目编号 为必填项")
    private java.lang.Long questionId;

    /**
     * 题目类型，0：单项选择题，1：多项选择题，2：填空题
     */
    @javax.validation.constraints.NotNull(message = "type 题目类型，0：单项选择题，1：多项选择题，2：填空题 为必填项")
    private java.lang.Integer type;

    /**
     * 单项选择题
     */
    @Transient
    private SingleSel singleSel;

    /**
     * 多项选择题
     */
    @Transient
    private MultiSel multiSel;

    /**
     * 填空题
     * TODO: 暂时不写
     */
//    private BUG;
}