package cn.edu.gzmu.service;

import cn.edu.gzmu.model.entity.Course;
import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.model.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Course Service
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
public interface CourseService extends BaseService<Course, Long> {

    /**
     * 通过学生查找所有课程——分页
     *
     * @param student  学生
     * @param pageable 分页信息
     * @return 结果
     */
    Page<Course> searchByStudent(Student student, Pageable pageable);

    /**
     * 通过教师查找所有课程 — 分页
     *
     * @param teacher  教师
     * @param pageable 分页信息
     * @return 课程信息
     */
    Page<Course> searchByTeacher(Teacher teacher, Pageable pageable);
}
