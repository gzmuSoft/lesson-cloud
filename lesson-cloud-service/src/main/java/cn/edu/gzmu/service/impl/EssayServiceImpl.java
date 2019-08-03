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
        entity.setCourse(
                courseRepository.findById(entity.getCourseId()).orElseThrow(
                        () -> new ResourceNotFoundException("Course class can not be find!")
                )
        );
        entity.setKnowledge(
                knowledgeRepository.findById(entity.getKnowledgeId()).orElseThrow(
                        () -> new ResourceNotFoundException("Parent class can not be find!")
                )
        );
        return entity.setSection(
                sectionRepository.findById(entity.getSectionId()).orElseThrow(
                        () -> new ResourceNotFoundException("Section class can not be find!")
                )
        );

    }

}