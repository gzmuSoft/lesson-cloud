package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Knowledge;
import cn.edu.gzmu.repository.entity.*;
import cn.edu.gzmu.service.KnowledgeService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * Knowledge Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class KnowledgeServiceImpl extends BaseServiceImpl<KnowledgeRepository, Knowledge, Long>
        implements KnowledgeService {

    private final @NonNull CourseRepository courseRepository;
    private final @NonNull SectionRepository sectionRepository;

    @Override
    public Knowledge completeEntity(Knowledge entity) {
        entity.setCourse(courseRepository.getOne(entity.getCourseId()));
        entity.setSection(sectionRepository.getOne(entity.getSectionId()));
        return entity;
    }
}
