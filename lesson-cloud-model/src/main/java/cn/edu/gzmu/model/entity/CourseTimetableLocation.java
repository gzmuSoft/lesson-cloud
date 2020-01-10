package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * course_timetable_location
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-27 10:59:08
 */
@Data
@ToString(callSuper = true)
@Table(name = "course_timetable_location")
@Entity(name = "course_timetable_location")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CourseTimetableLocation extends BaseEntity implements Serializable {

    /**
     * 逻辑班级编号
     */
    @javax.validation.constraints.NotNull(message = "logicClassId 逻辑班级编号 为必填项")
    private java.lang.Long logicClassId;

    /**
     * 以逗号作为分隔符的各个周序号，例如：1,3,5,7代表第1周、第3周、第5周和第7周上课
     */
    @Size(max = 255, message = "weeks 不能大于 255 位")
    private java.lang.String weeks;

    /**
     * 上课地点
     */
    @Size(max = 255, message = "location 不能大于 255 位")
    private java.lang.String location;

    /**
     * 星期几，1：星期一，2：星期二，3：星期三，4：星期四，5：星期五，6：星期六，7：星期天
     */
    private java.lang.Short weekday;

    /**
     * 上课是第几节，例如：第1节和第2节上课记录为1~2，第1节至第3节上课记录为1~2~3。如果同一天有多个分开的时间段，则以分号作为分隔符。例如：1~2;7~8
     */
    @Size(max = 255, message = "classSection 不能大于 255 位")
    private java.lang.String classSection;

    /**
     * 逻辑班级实体类
     */
    @Transient
    private LogicClass logicClass;
}