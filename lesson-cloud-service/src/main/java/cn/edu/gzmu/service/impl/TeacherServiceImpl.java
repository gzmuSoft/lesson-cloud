package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.*;
import cn.edu.gzmu.repository.entity.*;
import cn.edu.gzmu.service.TeacherService;
import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    /**
     * TODO: 待续
     */
    @Override
    public Page<Object> findPrivateQuestionBankCondition(Teacher teacher, Long courseId, Long sectionId, Long knowledgeId, String name, boolean isPublic, Pageable pageable) {
        return null;
    }


    @Override
    public JSONObject finPublicQuestionBankByCondition(Long courseId, Long sectionId, Long knowledgeId, String name, boolean isPublic, Pageable pageable) {
        JSONObject result = new JSONObject();
        result.put("essay", essayRepository.findAll(specification(courseId, sectionId, knowledgeId, name, isPublic), pageable));
        result.put("judgement", judgementRepository.findAll(specification(courseId, sectionId, knowledgeId, name, isPublic), pageable));
        result.put("multiSel", multiSelRepository.findAll(specification(courseId, sectionId, knowledgeId, name, isPublic), pageable));
        result.put("program", programRepository.findAll(specification(courseId, sectionId, knowledgeId, name, isPublic), pageable));
        result.put("singleSel", singleSelRepository.findAll(specification(courseId, sectionId, knowledgeId, name, isPublic), pageable));
        return result;
//        // 如果 name 不为空，则优先按题目内容模糊查询公开题目
//        if (StringUtils.isNoneEmpty(name)) {
//            return this.findByNameContainingAndIsPublic(name, isPublic, pageable);
//        }
//        // 如果没有课程 Id，则表示也没有更低级的条件，直接全查公开题目
//        else if (courseId == 0) {
//            return this.findAllByIsPublic(isPublic, pageable);
//        }
//        // 如果没有章节 Id，则表示也没有更低级的条件，则按课程 Id 查询公开题目
//        else if (sectionId == 0) {
//            return this.findAllByCourseIdAndIsPublic(courseId, isPublic, pageable);
//        }
//        // 如果没有知识点 Id，则按课程 Id 和章节 Id 查询公开题目
//        else if (knowledgeId == 0) {
//            return this.findAllByCourseIdAndSectionIdAndIsPublic(courseId, sectionId, isPublic, pageable);
//        }
//        // 如果所有条件都有，则按课程 Id ，章节 Id ，知识点 Id 查询公开题目
//        else {
//            return this.findAllByCourseIdAndSectionIdAndKnowledgeIdAndIsPublic(courseId, sectionId, knowledgeId, isPublic, pageable);
//        }
    }

    private <T> Specification<T> specification(Long courseId, Long sectionId, Long knowledgeId, String name, boolean isPublic) {
        return (root, query, criteriaBuilder) -> {
            Predicate conjunction = criteriaBuilder.conjunction();
            if (StringUtils.isNoneEmpty(name)) {
                conjunction = criteriaBuilder.and(conjunction,
                        criteriaBuilder.like(root.get("name").as(String.class), "%" + name + "%")
                );
            }
            if (courseId != 0) {
                conjunction = criteriaBuilder.and(conjunction,
                        criteriaBuilder.equal(root.get("courseId").as(Long.class), courseId)
                );
            }
            if (sectionId != 0) {
                conjunction = criteriaBuilder.and(conjunction,
                        criteriaBuilder.equal(root.get("sectionId").as(Long.class), sectionId)
                );
            }
            if (knowledgeId != 0) {
                conjunction = criteriaBuilder.and(conjunction,
                        criteriaBuilder.equal(root.get("knowledgeId").as(Long.class), knowledgeId)
                );
            }
            return query.where(
                    criteriaBuilder.and(conjunction,
                            criteriaBuilder.equal(root.get("isPublic").as(Boolean.class), isPublic))
            ).getRestriction();
        };
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
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
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
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
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
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
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
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }
}
