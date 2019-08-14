package cn.edu.gzmu.service;

import cn.edu.gzmu.model.dto.ExamDetailsDto;
import cn.edu.gzmu.model.entity.Exam;
import cn.edu.gzmu.model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * Exam Service
 *
 * @author echo
 * @author Japoul
 * @author ljq
 * @version 1.0
 * @date 2019-5-7 11:33:57
 *
 * <p>
 * 根据课程id和逻辑班级id列表查询考试信息
 * @date 2019-8-06 22:38:13
 *
 * <p>
 */
public interface ExamService extends BaseService<Exam, Long> {

    /**
     * 根据班级列表和课程信息分页查询考试信息
     *
     * @param courseId 课程信息
     * @param classIds 班级列表
     * @param pageable 分页
     * @return 结果
     */
    Page<Exam> searchByClassAndCourse(String courseId, String classIds, Pageable pageable);

    /**
     * 根据当前学生信息分页查询当前学生的考试信息
     *
     * @param student  学生信息
     * @param pageable 分页
     * @return 当前学生的考试信息
     */
    Page<Exam> searchByStudentPage(Student student, Pageable pageable);

    /**
     * 获取当前登录的学生的指定id所有考试详细信息
     *
     * @param student 学生信息
     * @param id      id
     * @return 考试详情
     */
    ExamDetailsDto searchDetailsById(Student student, Long id);

    /**
     * 获取所有考试的详细统计信息
     *
     * @return 考试详情
     */
    Page<ExamDetailsDto> searchDetailsAll(Pageable pageable);
}
