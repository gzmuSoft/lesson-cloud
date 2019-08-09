package cn.edu.gzmu.service;

import cn.edu.gzmu.model.entity.Exam;
import cn.edu.gzmu.model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * Exam Service
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 *
 * <p>
 * 根据课程id和逻辑班级id列表查询考试信息
 * @author Japoul
 * @date 2019-8-06 22:38:13
 *
 * <p>
 * @author ljq
 */
public interface ExamService extends BaseService<Exam, Long> {

    /**
     * 根据班级列表和课程信息分页查询考试信息
     *
     * @param courseId
     * @param classIds
     * @param pageable
     * @return
     */
    Page<Exam> searchByClassAndCourse(String courseId, String classIds, Pageable pageable);

    /**
     * 根据当前学生信息分页查询当前学生的考试信息
     *
     * @param student 学生信息
     * @param pageable 分页
     * @return 当前学生的考试信息
     */
    Page<Exam> searchByStudentPage(Student student, Pageable pageable);
}
