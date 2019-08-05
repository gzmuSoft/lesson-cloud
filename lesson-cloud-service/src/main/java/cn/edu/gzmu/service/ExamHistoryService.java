package cn.edu.gzmu.service;

import cn.edu.gzmu.model.entity.ExamHistory;
import cn.edu.gzmu.model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * ExamHistory Service
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
public interface ExamHistoryService extends BaseService<ExamHistory, Long> {

    /**
     * 分页查询获取考试历史信息
     *
     * @param student  当前学生对象
     * @param pageable 分页信息
     * @return 考试历史信息
     */
    Page<ExamHistory> searchByStudentPage(Student student, Pageable pageable);
}
