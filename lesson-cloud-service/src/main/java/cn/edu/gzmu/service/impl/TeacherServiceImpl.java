package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.BaseEntity;
import cn.edu.gzmu.model.entity.*;
import cn.edu.gzmu.repository.entity.KnowledgeQuestionRepository;
import cn.edu.gzmu.repository.entity.KnowledgeRepository;
import cn.edu.gzmu.repository.entity.QuestionRepository;
import cn.edu.gzmu.repository.entity.SectionRepository;
import cn.edu.gzmu.service.TeacherService;
import cn.edu.gzmu.service.helper.OauthHelper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.*;
import java.util.stream.Collectors;


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
    private final @NonNull QuestionRepository questionRepository;
    private final @NonNull KnowledgeQuestionRepository knowledgeQuestionRepository;
    private final @NonNull KnowledgeRepository knowledgeRepository;
    private final @NonNull SectionRepository sectionRepository;
    private final @NonNull OauthHelper oauthHelper;

    @Override
    public Page<Question> findQuestionBankCondition(Long courseId, Long passageId, Long sectionId, Long knowledgeId, String name, boolean isPublic, Pageable pageable) {
        return questionRepository.findAll((Specification<Question>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate conjunction = criteriaBuilder.equal(root.get("isEnable").as(Boolean.class), true);
            conjunction = criteriaBuilder.and(conjunction,
                    criteriaBuilder.equal(root.get("isPublic").as(Boolean.class), isPublic));
            if (BooleanUtils.isFalse(isPublic)) {
                conjunction = criteriaBuilder.and(conjunction,
                        criteriaBuilder.equal(root.get("createUser").as(String.class), oauthHelper.teacher().getName()));
            }
            if (StringUtils.isNoneBlank(name)) {
                conjunction = criteriaBuilder.and(conjunction,
                        criteriaBuilder.like(root.get("name").as(String.class), "%" + name + "%"));
            }
            CriteriaBuilder.In<Long> inIds = criteriaBuilder.in(root.get("id").as(Long.class));

            if (knowledgeId != 0) {
                List<KnowledgeQuestion> knowledgeQuestions = knowledgeQuestionRepository.findAllByKnowledgeId(knowledgeId);
                knowledgeQuestions.forEach(knowledgeQuestion -> inIds.value(knowledgeQuestion.getQuestionId()));
            } else if (sectionId != 0) {
                List<Knowledge> knowledgeList = knowledgeRepository.findAllBySectionId(sectionId);
                List<Long> knowledgeIds = knowledgeList.stream().map(Knowledge::getId).collect(Collectors.toList());
                List<KnowledgeQuestion> knowledgeQuestionList = knowledgeQuestionRepository.findAllByKnowledgeIdIn(knowledgeIds);
                knowledgeQuestionList.forEach(knowledgeQuestion -> inIds.value(knowledgeQuestion.getQuestionId()));
            } else if (passageId != 0) {
                List<Section> sectionList = sectionRepository.findAllByParentId(passageId);
                //TODO 代码冗余 可以尝试优化一下
                List<Long> sectionIds = sectionList.stream().map(Section::getId).collect(Collectors.toList());
                //这里需要注意一下 章Id也需要加入到查询列表里面
                sectionIds.add(passageId);
                List<Knowledge> knowledgeList = knowledgeRepository.findAllBySectionIdIn(sectionIds);
                List<Long> knowledgeIds = knowledgeList.stream().map(Knowledge::getId).collect(Collectors.toList());
                List<KnowledgeQuestion> knowledgeQuestionList = knowledgeQuestionRepository.findAllByKnowledgeIdIn(knowledgeIds);
                knowledgeQuestionList.forEach(knowledgeQuestion -> inIds.value(knowledgeQuestion.getQuestionId()));
            } else if (courseId != 0) {
                // 注意知识点和节对应，过滤掉章
                List<Long> sectionIds = sectionRepository.findAllByCourseId(courseId).stream().map(Section::getId).collect(Collectors.toList());
                List<Long> knowledgeIds = knowledgeRepository.findAllBySectionIdIn(sectionIds)
                        .stream().map(Knowledge::getId)
                        .collect(Collectors.toList());
                Set<Long> questionIdSet = knowledgeQuestionRepository.findAllByKnowledgeIdIn(knowledgeIds)
                        .stream().map(KnowledgeQuestion::getQuestionId)
                        .collect(Collectors.toSet());
                questionIdSet.forEach(inIds::value);
            }
            conjunction = criteriaBuilder.and(conjunction, inIds);
            return criteriaQuery.where(conjunction).getRestriction();
        }, pageable);
    }

}
