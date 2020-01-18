package cn.edu.gzmu.service;

import cn.edu.gzmu.model.dto.QuestionView;
import cn.edu.gzmu.model.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Teacher Service
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
public interface TeacherService {
    /**
     * 通过不同条件获取题库
     *
     * @param teacher     当前登录教师
     * @param courseId    课程Id
     * @param sectionId   章节Id
     * @param knowledgeId 知识点Id
     * @param isPublic    是否公开
     * @param pageable    分页
     * @author Soul
     * @date 2020/1/12 1:16
     */
    Page<QuestionView> findQuestionBankCondition(Teacher teacher, Long courseId, Long sectionId, Long knowledgeId, String name, boolean isPublic, Pageable pageable);
}
