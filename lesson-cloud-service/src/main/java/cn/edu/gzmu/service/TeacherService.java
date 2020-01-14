package cn.edu.gzmu.service;

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
     * 通过不同条件获取属于该教师的私人题库
     *
     * @param teacher  当前登录教师
     * @param pageable 分页
     * @return org.springframework.data.domain.Page<java.lang.Object>
     * @author Soul
     * @date 2020/1/12 1:16
     */
    Page<Object> findPrivateQuestionBankCondition(Teacher teacher, boolean isPublic, Pageable pageable);

    /**
     * 通过不同条件获取公有题库
     *
     * @param courseId    课程Id
     * @param sectionId   章节Id
     * @param knowledgeId 知识点Id
     * @param isPublic    是否公开
     * @param pageable    分页
     * @author Soul
     * @date 2020/1/14 0:05
     */
    Page<Object> finPublicQuestionBankByCondition(Long courseId, Long sectionId, Long knowledgeId, String name, boolean isPublic, Pageable pageable);
}
