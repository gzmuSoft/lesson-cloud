package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;

/**
 * appeal
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-27 10:59:08
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Appeal extends BaseEntity {

    /**
     * 学校编号
     */
    @javax.validation.constraints.NotNull(message = "schoolId 学校编号 为必填项")
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