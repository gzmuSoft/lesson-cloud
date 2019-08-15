package cn.edu.gzmu.service;

import cn.edu.gzmu.model.dto.ExamDetailsDto;
import cn.edu.gzmu.model.entity.Exam;
import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.model.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * Exam Service
 *
 * @author echo
 * @author Japoul
 * @author ljq
 * @author hzl
 * @author YMS
 * @version 1.0
 * @date 2019-5-7 11:33:57
 *
 * <p>
 * 根据课程id和逻辑班级id列表查询考试信息
 * @date 2019-8-06 22:38:13
 *
 * <p>
 *
 * <p>
 * @date 2019-8-13 23:48:10
 * 获取到当前教师未发布的考试信息
 *
 * </p>
 * @date 2019-8-14 11:18
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
    Page<Exam> searchByLogicClassAndCourse(String courseId, String classIds, Pageable pageable);

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

//    /**
//     * 获取到当前教师已发布的考试信息根据classid
//     *
//     * @param classIds 班级列表
//     * @param courseIds 课程信息
//     * @param pageable 分页
//     * @return 考试详情
//     */
//    Page<Exam> searchExamFromPublish(String classIds, String courseIds , Pageable pageable);

//    /**
//     * 获取到当前教师未发布的考试信息
//     *
//     * @param teacher 当前登陆的教师信息
//     * @param logicClassIds logicClassIds
//     * @param courseId 课程id
//     * @param pageable pageable
//     * @return page
//     */
//    Page<Exam> searchExamFromUnPublish(Teacher teacher, String logicClassIds, String courseId, Pageable pageable);

    /**
     * 获取所有考试的详细统计信息
     *
     * @param pageable      分页
     * @param semesterId    学期 id
     * @return 考试详情
     */
    Page<ExamDetailsDto> searchDetailsAll(String semesterId, Pageable pageable);

    /**
     * 获取到当前教师已发布的考试信息
     *
     * @param teacher       当前登陆的教师信息
     * @param logicClassIds logicClassIds
     * @param courseId      课程id
     * @param pageable      pageable
     * @param publish       是否发布
     * @return page
     */
    Page<Exam> searchExamFromPublishStatus(Teacher teacher, String logicClassIds, String courseId, Pageable pageable, boolean publish);


    /**
     * 获取到当前学生考试信息
     *
     * @param student       当前登陆的学生
     * @param pageable      pageable
     * @param type  是否重修班
     * @param finishFlag 根据flag获取 完成:1 未完成:2 我全都要！:0 的数据
     * @return page
     */
    Page<ExamDetailsDto> searchDetailsByStudentUnPage(Student student, Pageable pageable,Boolean type,Integer finishFlag);

}
