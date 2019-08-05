package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.MultiSel;
import cn.edu.gzmu.repository.entity.CourseRepository;
import cn.edu.gzmu.repository.entity.KnowledgeRepository;
import cn.edu.gzmu.repository.entity.MultiSelRepository;
import cn.edu.gzmu.repository.entity.SectionRepository;
import cn.edu.gzmu.service.MultiSelService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * MultiSel Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class MultiSelServiceImpl extends BaseServiceImpl<MultiSelRepository, MultiSel, Long>
        implements MultiSelService {

    private @NonNull SectionRepository sectionRepository;
    private @NonNull CourseRepository courseRepository;
    private @NonNull KnowledgeRepository knowledgeRepository;

    @Override
    protected MultiSel completeEntity(MultiSel entity) {
        return entity.setSection(sectionRepository.findById(entity.getSectionId()).orElse(null))
                .setCourse(courseRepository.findById(entity.getCourseId()).orElse(null))
                .setKnowledge(knowledgeRepository.findById(entity.getKnowledgeId()).orElse(null));
    }
}
