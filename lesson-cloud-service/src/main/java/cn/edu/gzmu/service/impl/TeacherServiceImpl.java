package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.*;
import cn.edu.gzmu.repository.entity.*;
import cn.edu.gzmu.service.TeacherService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Teacher Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final @NonNull EssayRepository essayRepository;
    private final @NonNull JudgementRepository judgementRepository;
    private final @NonNull MultiSelRepository multiSelRepository;
    private final @NonNull ProgramRepository programRepository;
    private final @NonNull SingleSelRepository singleSelRepository;

    // 待续
    @Override
    public Page<Object> findPrivateQuestionBankCondition(Teacher teacher, boolean isPublic, Pageable pageable) {
        return null;
    }


    @Override
    public Page<Object> finPublicQuestionBankByCondition(Long courseId, Long sectionId, Long knowledgeId, String name, boolean isPublic, Pageable pageable) {
        if (!name.equals("")) {
            return this.findByNameContainingAndIsPublic(name, isPublic, pageable);
        } else if (courseId == 0) {
            return this.findAllByIsPublic(isPublic, pageable);
        } else if (sectionId == 0) {
            return this.findAllByCourseIdAndIsPublic(courseId, isPublic, pageable);
        } else if (knowledgeId == 0) {
            return this.findAllByCourseIdAndSectionIdAndIsPublic(courseId, sectionId, isPublic, pageable);
        } else {
            return this.findAllByCourseIdAndSectionIdAndKnowledgeIdAndIsPublic(courseId, sectionId, knowledgeId, isPublic, pageable);
        }
    }

    /**
     * 获取所有的题目
     *
     * @param isPublic 是否公开
     * @param pageable 分页
     * @return org.springframework.data.domain.Page<java.lang.Object>
     * @author Soul
     * @date 2020/1/14 0:07
     */
    public Page<Object> findAllByIsPublic(boolean isPublic, Pageable pageable) {
        // 创建 List<Object> 存放不同类型题目的查询结果
        List<Object> list = new ArrayList<>();
        // 添加问答题
        list.add(essayRepository.findAllByIsPublic(isPublic, pageable));
        // 添加判断题
        list.add(judgementRepository.findAllByIsPublic(isPublic, pageable));
        // 添加多选题
        list.add(multiSelRepository.findAllByIsPublic(isPublic, pageable));
        // 添加编程题
        list.add(programRepository.findAllByIsPublic(isPublic, pageable));
        // 添加单选题
        list.add(singleSelRepository.findAllByIsPublic(isPublic, pageable));
        // 为了使List转为Page达到分页效果，采取手动分割方式
        // start表示每页List起始位置
        int start = (int) pageable.getOffset();
        // end表示每页List终止位置
        int end = Math.min((start + pageable.getPageSize()), list.size());
        // new一个PageImpl实现分页
        return new PageImpl<Object>(list.subList(start, end), pageable, list.size());
    }

    /**
     * 获取某课程的题目
     *
     * @param courseId 课程Id
     * @param isPublic 是否公开
     * @param pageable 分页
     * @return org.springframework.data.domain.Page<java.lang.Object>
     * @author Soul
     * @date 2020/1/14 0:09
     */
    public Page<Object> findAllByCourseIdAndIsPublic(Long courseId, boolean isPublic, Pageable pageable) {
        // 创建 List<Object> 存放不同类型题目的查询结果
        List<Object> list = new ArrayList<>();
        // 添加问答题
        list.add(essayRepository.findAllByCourseIdAndIsPublic(courseId, isPublic, pageable));
        // 添加判断题
        list.add(judgementRepository.findAllByCourseIdAndIsPublic(courseId, isPublic, pageable));
        // 添加多选题
        list.add(multiSelRepository.findAllByCourseIdAndIsPublic(courseId, isPublic, pageable));
        // 添加编程题
        list.add(programRepository.findAllByCourseIdAndIsPublic(courseId, isPublic, pageable));
        // 添加单选题
        list.add(singleSelRepository.findAllByCourseIdAndIsPublic(courseId, isPublic, pageable));
        // 为了使List转为Page达到分页效果，采取手动分割方式
        // start表示每页List起始位置
        int start = (int) pageable.getOffset();
        // end表示每页List终止位置
        int end = Math.min((start + pageable.getPageSize()), list.size());
        // new一个PageImpl实现分页
        return new PageImpl<Object>(list.subList(start, end), pageable, list.size());
    }

    /**
     * 获取某课程某章节的题目
     *
     * @param courseId  课程Id
     * @param sectionId 章节Id
     * @param isPublic  是否公开
     * @param pageable  分页
     * @return org.springframework.data.domain.Page<java.lang.Object>
     * @author Soul
     * @date 2020/1/14 0:11
     */
    public Page<Object> findAllByCourseIdAndSectionIdAndIsPublic(Long courseId, Long sectionId, boolean isPublic, Pageable pageable) {
        // 创建 List<Object> 存放不同类型题目的查询结果
        List<Object> list = new ArrayList<>();
        // 添加问答题
        list.add(essayRepository.findAllByCourseIdAndSectionIdAndIsPublic(courseId, sectionId, isPublic, pageable));
        // 添加判断题
        list.add(judgementRepository.findAllByCourseIdAndSectionIdAndIsPublic(courseId, sectionId, isPublic, pageable));
        // 添加多选题
        list.add(multiSelRepository.findAllByCourseIdAndSectionIdAndIsPublic(courseId, sectionId, isPublic, pageable));
        // 添加编程题
        list.add(programRepository.findAllByCourseIdAndSectionIdAndIsPublic(courseId, sectionId, isPublic, pageable));
        // 添加单选题
        list.add(singleSelRepository.findAllByCourseIdAndSectionIdAndIsPublic(courseId, sectionId, isPublic, pageable));
        // 为了使List转为Page达到分页效果，采取手动分割方式
        // start表示每页List起始位置
        int start = (int) pageable.getOffset();
        // end表示每页List终止位置
        int end = Math.min((start + pageable.getPageSize()), list.size());
        // new一个PageImpl实现分页
        return new PageImpl<Object>(list.subList(start, end), pageable, list.size());
    }

    /**
     * 获取某课程某章节某知识点的题目
     *
     * @param courseId    课程Id
     * @param sectionId   章节Id
     * @param knowledgeId 知识点Id
     * @param isPublic    是否公开
     * @param pageable    分页
     * @return org.springframework.data.domain.Page<java.lang.Object>
     * @author Soul
     * @date 2020/1/14 0:14
     */
    public Page<Object> findAllByCourseIdAndSectionIdAndKnowledgeIdAndIsPublic(Long courseId, Long sectionId, Long knowledgeId, boolean isPublic, Pageable pageable) {
        // 创建 List<Object> 存放不同类型题目的查询结果
        List<Object> list = new ArrayList<>();
        // 添加问答题
        list.add(essayRepository.findAllByCourseIdAndSectionIdAndKnowledgeIdAndIsPublic(courseId, sectionId, knowledgeId, isPublic, pageable));
        // 添加判断题
        list.add(judgementRepository.findAllByCourseIdAndSectionIdAndKnowledgeIdAndIsPublic(courseId, sectionId, knowledgeId, isPublic, pageable));
        // 添加多选题
        list.add(multiSelRepository.findAllByCourseIdAndSectionIdAndKnowledgeIdAndIsPublic(courseId, sectionId, knowledgeId, isPublic, pageable));
        // 添加编程题
        list.add(programRepository.findAllByCourseIdAndSectionIdAndKnowledgeIdAndIsPublic(courseId, sectionId, knowledgeId, isPublic, pageable));
        // 添加单选题
        list.add(singleSelRepository.findAllByCourseIdAndSectionIdAndKnowledgeIdAndIsPublic(courseId, sectionId, knowledgeId, isPublic, pageable));
        // 为了使List转为Page达到分页效果，采取手动分割方式
        // start表示每页List起始位置
        int start = (int) pageable.getOffset();
        // end表示每页List终止位置
        int end = Math.min((start + pageable.getPageSize()), list.size());
        // new一个PageImpl实现分页
        return new PageImpl<Object>(list.subList(start, end), pageable, list.size());
    }

    /**
     * 获取某字符串匹配的题目
     *
     * @param name     名称
     * @param isPublic 是否公开
     * @param pageable 分页
     * @return org.springframework.data.domain.Page<java.lang.Object>
     * @author Soul
     * @date 2020/1/14 0:17
     */
    public Page<Object> findByNameContainingAndIsPublic(String name, boolean isPublic, Pageable pageable) {
        // 创建 List<Object> 存放不同类型题目的查询结果
        List<Object> list = new ArrayList<>();
        // 添加问答题
        list.add(essayRepository.findByNameContainingAndIsPublic(name, isPublic, pageable));
        // 添加判断题
        list.add(judgementRepository.findByNameContainingAndIsPublic(name, isPublic, pageable));
        // 添加多选题
        list.add(multiSelRepository.findByNameContainingAndIsPublic(name, isPublic, pageable));
        // 添加编程题
        list.add(programRepository.findByNameContainingAndIsPublic(name, isPublic, pageable));
        // 添加单选题
        list.add(singleSelRepository.findByNameContainingAndIsPublic(name, isPublic, pageable));
        // 为了使List转为Page达到分页效果，采取手动分割方式
        // start表示每页List起始位置
        int start = (int) pageable.getOffset();
        // end表示每页List终止位置
        int end = Math.min((start + pageable.getPageSize()), list.size());
        // new一个PageImpl实现分页
        return new PageImpl<Object>(list.subList(start, end), pageable, list.size());
    }
}
