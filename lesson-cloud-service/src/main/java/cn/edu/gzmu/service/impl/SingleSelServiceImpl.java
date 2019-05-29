package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.SingleSel;
import cn.edu.gzmu.repository.entity.CourseRepository;
import cn.edu.gzmu.repository.entity.KnowledgeRepository;
import cn.edu.gzmu.repository.entity.SectionRepository;
import cn.edu.gzmu.repository.entity.SingleSelRepository;
import cn.edu.gzmu.service.SingleSelService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * SingleSel Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class SingleSelServiceImpl extends BaseServiceImpl<SingleSelRepository, SingleSel, Long>
        implements SingleSelService {

    private final @NonNull CourseRepository courseRepository;
    private final @NonNull SectionRepository sectionRepository;
    private final @NonNull KnowledgeRepository knowledgeRepository;

    @Override
    public SingleSel completeEntity(SingleSel entity) {
        entity.setCourse(courseRepository.getOne(entity.getCourseId()));
        entity.setSection(sectionRepository.getOne(entity.getSectionId()));
        entity.setKnowledge(knowledgeRepository.getOne(entity.getKnowledgeId()));
        return entity;
    }
}
