package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Exam;
import cn.edu.gzmu.model.entity.LogicClass;
import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.repository.entity.ExamRepository;
import cn.edu.gzmu.repository.entity.LogicClassRepository;
import cn.edu.gzmu.service.StudentService;
import cn.edu.gzmu.service.helper.OauthHelper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


/**
 * Student Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final @NonNull LogicClassRepository logicClassRepository;
    private final @NonNull ExamRepository examRepository;
    private final @NonNull OauthHelper oauthHelper;


    @Override
    public Page<LogicClass> findLogicClassBySemesterId(Long semesterId, Pageable pageable) {
        Student student = oauthHelper.student();
        return logicClassRepository.findAll((Specification<LogicClass>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate conjunction = criteriaBuilder.equal(root.get("semesterId").as(Long.class), semesterId);
            Predicate conjunctionClass = criteriaBuilder.equal(root.get("classesId").as(Long.class), student.getClassesId());
            conjunctionClass = criteriaBuilder.and(conjunctionClass,
                    criteriaBuilder.equal(root.get("type").as(Boolean.class), Boolean.FALSE));
            //查出重修的学生logicClassList
            List<LogicClass> studentLogicClassList = logicClassRepository.findAllByTypeAndStudentIdAndSemesterId(Boolean.TRUE, student.getId(), semesterId);
            CriteriaBuilder.In<Long> inIds = criteriaBuilder.in(root.get("id").as(Long.class));
            studentLogicClassList.forEach(logicClass -> inIds.value(logicClass.getClassesId()));
            conjunction = criteriaBuilder.and(conjunction, criteriaBuilder.or(conjunctionClass, inIds));
            return criteriaQuery.where(conjunction).getRestriction();
        }, pageable);
    }

    @Override
    public Page<Exam> findExamByCourseId(Long courseId, Pageable pageable) {
        return examRepository.findAllByCourseId(courseId, pageable);
    }
}
