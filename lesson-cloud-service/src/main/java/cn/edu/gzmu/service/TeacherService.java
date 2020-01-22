package cn.edu.gzmu.service;

import cn.edu.gzmu.model.entity.Question;
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
     * @param passageId   章Id
     * @param sectionId   节Id
     * @param knowledgeId 知识点Id
     * @param isPublic    是否公开
     * @param pageable    分页
     * @param name        名称
     * @author Soul
     * @return 结果
     * @date 2020/1/12 1:16
     */
    Page<Question> findQuestionBankCondition(Long passageId, Long sectionId, Long knowledgeId, String name, boolean isPublic, Pageable pageable);
}
