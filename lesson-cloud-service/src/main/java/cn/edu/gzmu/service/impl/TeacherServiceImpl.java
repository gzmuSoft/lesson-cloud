package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.dto.QuestionView;
import cn.edu.gzmu.model.entity.*;
import cn.edu.gzmu.repository.dto.QuestionViewRepository;
import cn.edu.gzmu.service.TeacherService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;


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
    private final @NonNull QuestionViewRepository questionViewRepository;

    @Override
    public Page<QuestionView> findQuestionBankCondition(Teacher teacher, Long courseId, Long sectionId, Long knowledgeId, String name, boolean isPublic, Pageable pageable) {
        return questionViewRepository.findAll(specificationPrivate(teacher, courseId, sectionId, knowledgeId, name, isPublic), pageable);
    }

    private <T> Specification<T> specificationPrivate(Teacher teacher, Long courseId, Long sectionId, Long knowledgeId, String name, boolean isPublic) {
        return (root, query, criteriaBuilder) -> {
            Predicate conjunction = criteriaBuilder.equal(root.get("isPublic").as(Boolean.class), isPublic);
            if (!isPublic) {
                conjunction = criteriaBuilder.and(conjunction,
                        criteriaBuilder.equal(root.get("createUser").as(Long.class), teacher.getName())
                );
            }
            if (StringUtils.isNoneEmpty(name)) {
                conjunction = criteriaBuilder.and(conjunction,
                        criteriaBuilder.like(root.get("name").as(String.class), "%" + name + "%")
                );
            }
            if (courseId == 0) {
                return query.where(conjunction).getRestriction();
            }
            conjunction = criteriaBuilder.and(conjunction,
                    criteriaBuilder.equal(root.get("courseId").as(Long.class), courseId)
            );
            if (sectionId == 0) {
                return query.where(conjunction).getRestriction();
            }
            conjunction = criteriaBuilder.and(conjunction,
                    criteriaBuilder.equal(root.get("sectionId").as(Long.class), sectionId)
            );
            if (knowledgeId == 0) {
                return query.where(conjunction).getRestriction();
            }
            conjunction = criteriaBuilder.and(conjunction,
                    criteriaBuilder.equal(root.get("knowledgeId").as(Long.class), knowledgeId)
            );
            return query.where(conjunction).getRestriction();
        };
    }
}
