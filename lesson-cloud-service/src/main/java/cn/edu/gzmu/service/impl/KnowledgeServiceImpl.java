package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Knowledge;
import cn.edu.gzmu.repository.entity.CourseRepository;
import cn.edu.gzmu.repository.entity.KnowledgeRepository;
import cn.edu.gzmu.repository.entity.SectionRepository;
import cn.edu.gzmu.service.KnowledgeService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Knowledge Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 *
 * @author ypx
 */
@Service
@RequiredArgsConstructor
public class KnowledgeServiceImpl extends BaseServiceImpl<KnowledgeRepository, Knowledge, Long>
        implements KnowledgeService {
    private final @NonNull CourseRepository courseRepository;
    private final @NonNull SectionRepository sectionRepository;
    private final @NonNull KnowledgeRepository knowledgeRepository;

    @Override
    protected Knowledge completeEntity(Knowledge entity) {
        return entity
                .setCourse(courseRepository.findById(entity.getCourseId()).orElse(null))
                .setParent(knowledgeRepository.findById(entity.getParentId()).orElse(null))
                .setSection(sectionRepository.findById(entity.getSectionId()).orElse(null));

    }

    @Override
    public List<Knowledge> searchBySectionIdAndCourseId(Long sectionId,Long courseId) {
        return knowledgeRepository.findAllBySectionIdAndCourseId(sectionId,courseId);
    }
}
