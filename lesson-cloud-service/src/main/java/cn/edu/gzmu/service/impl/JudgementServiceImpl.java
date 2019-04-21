package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Judgement;
import cn.edu.gzmu.repository.entity.CourseRepository;
import cn.edu.gzmu.repository.entity.JudgementRepository;
import cn.edu.gzmu.repository.entity.KnowledgeRepository;
import cn.edu.gzmu.repository.entity.SectionRepository;
import cn.edu.gzmu.service.JudgementService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



/**
 * Judgement Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@RequiredArgsConstructor
@Service
public class JudgementServiceImpl extends BaseServiceImpl<JudgementRepository, Judgement, Long>
        implements JudgementService {

    private final @NonNull JudgementRepository judgementRepository;
    private final @NonNull CourseRepository courseRepository;
    private final @NonNull SectionRepository sectionRepository;
    private final @NonNull KnowledgeRepository knowledgeRepository;

    @Override
    public Page<Judgement> searchAll(Pageable pageable) {
        return judgementRepository.findAll(pageable).map(judgement ->{

            if(judgement.getCourseId() != null){
                judgement.setCourse(courseRepository.getOne(judgement.getCourseId()));
            }
            if(judgement.getSectionId() != null){
                judgement.setSection(sectionRepository.getOne(judgement.getSectionId()));
            }
            if (judgement.getKnowledgeId() != null){
                judgement.setKnowledge(knowledgeRepository.getOne(judgement.getKnowledgeId()));
            }
            return judgement;
                });
    }
}
