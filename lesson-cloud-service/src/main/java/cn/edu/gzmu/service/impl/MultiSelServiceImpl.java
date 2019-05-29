package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.MultiSel;
import cn.edu.gzmu.repository.entity.CourseRepository;
import cn.edu.gzmu.repository.entity.KnowledgeRepository;
import cn.edu.gzmu.repository.entity.MultiSelRepository;
import cn.edu.gzmu.repository.entity.SectionRepository;
import cn.edu.gzmu.service.MultiSelService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * MultiSel Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@RequiredArgsConstructor
@Service
public class MultiSelServiceImpl extends BaseServiceImpl<MultiSelRepository, MultiSel, Long>
        implements MultiSelService {

    private  final @NonNull MultiSelRepository multiSelRepository;
    private final @NonNull CourseRepository courseRepository;
    private final @NonNull SectionRepository sectionRepository;
    private final @NonNull KnowledgeRepository knowledgeRepository;

    @Override
    public Page<MultiSel> searchAll(Pageable pageable) {
        return multiSelRepository.findAll(pageable).map(multiSel -> {
            if (multiSel.getCourseId() != null) {
                multiSel.setCourse(courseRepository.getOne(multiSel.getCourseId()));
            }
            if (multiSel.getSectionId() != null) {
                multiSel.setSection(sectionRepository.getOne(multiSel.getSectionId()));
            }
            if (multiSel.getKnowledgeId() != null) {
                multiSel.setKnowledge(knowledgeRepository.getOne(multiSel.getKnowledgeId()));
            }
            return multiSel;
        });
    }
}
