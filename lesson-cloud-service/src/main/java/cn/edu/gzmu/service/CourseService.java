package cn.edu.gzmu.service;

import cn.edu.gzmu.model.entity.Course;
import cn.edu.gzmu.model.entity.Student;

import java.util.List;
import java.util.Set;


/**
 * Course Service
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
public interface CourseService extends BaseService<Course, Long> {

    /**
     * 通过学生查找所有课程
     *
     * @param student 学生
     * @return 课程信息
     */
    List<Course> searchByStudent(Student student);
}
