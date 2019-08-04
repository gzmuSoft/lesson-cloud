package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Essay;
import cn.edu.gzmu.model.entity.Knowledge;
import cn.edu.gzmu.model.exception.ResourceNotFoundException;
import cn.edu.gzmu.repository.entity.CourseRepository;
import cn.edu.gzmu.repository.entity.EssayRepository;
import cn.edu.gzmu.repository.entity.KnowledgeRepository;
import cn.edu.gzmu.repository.entity.SectionRepository;
import cn.edu.gzmu.service.EssayService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * Essay Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class EssayServiceImpl extends BaseServiceImpl<EssayRepository, Essay, Long>
        implements EssayService {
    private final @NonNull CourseRepository courseRepository;
    private final @NonNull SectionRepository sectionRepository;
    private final @NonNull KnowledgeRepository knowledgeRepository;

    @Override
    protected Essay completeEntity(Essay entity) {
        return entity
                .setCourse(courseRepository.findById(entity.getCourseId()).orElse(null))
                .setKnowledge(knowledgeRepository.findById(entity.getKnowledgeId()).orElse(null))
                .setSection(sectionRepository.findById(entity.getSectionId()).orElse(null));

    }

}