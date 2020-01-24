package cn.edu.gzmu.service;


import cn.edu.gzmu.model.entity.Exam;
import cn.edu.gzmu.model.entity.LogicClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Student Service
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
public interface StudentService {
    /**
     * 根据学期，查询当前学生所在的所有逻辑班级
     *
     * @param semesterId 学期id
     * @param pageable   分页信息
     * @return page 分页结果
     */
    Page<LogicClass> findLogicClassBySemesterId(Long semesterId, Pageable pageable);

    /**
     * 根据课程id查询当前学生的考试信息
     *
     * @param courseId 课程id
     * @param pageable 分页信息
     * @return page 分页结果
     */
    Page<Exam> findExamByCourseId(Long courseId, Pageable pageable);
}
