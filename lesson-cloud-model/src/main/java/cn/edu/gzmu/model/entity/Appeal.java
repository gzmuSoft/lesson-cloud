package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
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
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * appeal
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-20 11:34:28
 */
@Data
@Cacheable
@ToString(callSuper = true)
@Table(name = "appeal")
@Entity(name = "appeal")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Cache(region = "appeal", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Appeal extends BaseEntity implements Serializable {

    /**
     * 学校编号
     */
    @javax.validation.constraints.NotNull(message = "schoolId 为必填项")
    private java.lang.Long schoolId;

    /**
     * 学号
     */
    @Size(max = 20, message = "studentNo 不能大于 20 位")
    private java.lang.String studentNo;

    /**
     * 学生姓名
     */
    @Size(max = 255, message = "studentName 不能大于 255 位")
    private java.lang.String studentName;

    /**
     * 学生身份证号
     */
    @Size(max = 18, message = "idNumber 不能大于 18 位")
    private java.lang.String idNumber;

    /**
     * 申诉邮箱，用于接收申诉处理结果
     */
    @Size(max = 255, message = "email 不能大于 255 位")
    @javax.validation.constraints.Email(message = "email不合法，请输入正确的邮箱地址")
    private java.lang.String email;

    /**
     * 上传的身份证照片保存路径
     */
    @Size(max = 255, message = "idPath 不能大于 255 位")
    private java.lang.String idPath;

    /**
     * 上传的学生证照片保存路径
     */
    @Size(max = 255, message = "studentCardPath 不能大于 255 位")
    private java.lang.String studentCardPath;
}