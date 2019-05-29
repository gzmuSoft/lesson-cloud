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
@RequiredArgsConstructor
@Service
public class SingleSelServiceImpl extends BaseServiceImpl<SingleSelRepository, SingleSel, Long>
        implements SingleSelService {

    private final @NonNull SingleSelRepository singleSelRepository;
    private final @NonNull CourseRepository courseRepository;
    private final @NonNull SectionRepository sectionRepository;
    private final @NonNull KnowledgeRepository knowledgeRepository;

    @Override
    public Page<SingleSel> searchAll(Pageable pageable) {
        return singleSelRepository.findAll(pageable).map(singleSel -> {
            if (singleSel.getCourseId() != null) {
                singleSel.setCourse(courseRepository.getOne(singleSel.getCourseId()));
            }
            if (singleSel.getSectionId() != null) {
                singleSel.setSection(sectionRepository.getOne(singleSel.getSectionId()));
            }
            if (singleSel.getKnowledgeId() != null) {
                singleSel.setKnowledge(knowledgeRepository.getOne(singleSel.getKnowledgeId()));
            }
            return singleSel;
        });
    }
}
