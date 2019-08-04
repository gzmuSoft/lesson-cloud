package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.SingleSel;
import cn.edu.gzmu.model.exception.ResourceNotFoundException;
import cn.edu.gzmu.repository.entity.CourseRepository;
import cn.edu.gzmu.repository.entity.KnowledgeRepository;
import cn.edu.gzmu.repository.entity.SectionRepository;
import cn.edu.gzmu.repository.entity.SingleSelRepository;
import cn.edu.gzmu.service.SingleSelService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * SingleSel Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 *
 * 单项选择题联查课程,章节,知识点
 * @author Japoul
 * @date 2019-8-4 23:33:57
 */
@Service
@RequiredArgsConstructor
public class SingleSelServiceImpl extends BaseServiceImpl<SingleSelRepository, SingleSel, Long>
        implements SingleSelService {

    private final @NonNull CourseRepository courseRepository;
    private final @NonNull KnowledgeRepository knowledgeRepository;
    private final @NonNull SectionRepository sectionRepository;

    @Override
    protected SingleSel completeEntity(SingleSel entity) {
        entity.setCourse(courseRepository.findById(entity.getCourseId()).orElse(null));
        entity.setKnowledge(knowledgeRepository.findById(entity.getKnowledgeId()).orElse(null));
        entity.setSection(sectionRepository.findById(entity.getSectionId()).orElse(null));
        return entity;
    }

}
