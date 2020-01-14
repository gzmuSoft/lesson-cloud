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

import java.util.List;


/**
 * Judgement Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class JudgementServiceImpl extends BaseServiceImpl<JudgementRepository, Judgement, Long>
        implements JudgementService {
    private final @NonNull JudgementRepository judgementRepository;
    private final @NonNull CourseRepository courseRepository;
    private final @NonNull SectionRepository sectionRepository;
    private final @NonNull KnowledgeRepository knowledgeRepository;

    @Override
    protected Judgement completeEntity(Judgement entity) {
        return entity.setCourse(courseRepository.findById(entity.getCourseId()).orElse(null))
                .setSection(sectionRepository.findById(entity.getSectionId()).orElse(null))
                .setKnowledge(knowledgeRepository.findById(entity.getKnowledgeId()).orElse(null));
    }

    @Override
    public Page<Judgement> findAllByIsPublic(boolean isPublic, Pageable pageable) {
        // 先根据 isPublic 查出当前课程所有判断题
        Page<Judgement> judgements = judgementRepository.findAllByIsPublic(isPublic, pageable);
        // 获取到分页信息中的数据
        List<Judgement> content = judgements.getContent();
        // 对数据进行遍历
        for (Judgement judgement : content) {
            // 对每个数据进行完整性填充
            completeEntity(judgement);
        }
        return judgements;
    }

    @Override
    public Page<Judgement> findAllByCourseIdAndIsPublic(Long courseId, boolean isPublic, Pageable pageable) {
        // 先根据 courseId 查出当前课程所有判断题
        Page<Judgement> judgements = judgementRepository.findAllByCourseIdAndIsPublic(courseId, isPublic, pageable);
        // 获取到分页信息中的数据
        List<Judgement> content = judgements.getContent();
        // 对数据进行遍历
        for (Judgement judgement : content) {
            // 对每个数据进行完整性填充
            completeEntity(judgement);
        }
        return judgements;
    }

    @Override
    public Page<Judgement> findAllByCourseIdAndSectionIdAndIsPublic(Long courseId, Long sectionId, boolean isPublic, Pageable pageable) {
        // 先根据 courseId 和 sectionId 查出当前章节所有判断题
        Page<Judgement> judgements = judgementRepository.findAllByCourseIdAndSectionIdAndIsPublic(courseId, sectionId, isPublic, pageable);
        // 获取到分页信息中的数据
        List<Judgement> content = judgements.getContent();
        // 对数据进行遍历
        for (Judgement judgement : content) {
            // 对每个数据进行完整性填充
            completeEntity(judgement);
        }
        return judgements;
    }

    @Override
    public Page<Judgement> findAllByCourseIdAndSectionIdAndKnowledgeIdAndIsPublic(Long courseId, Long sectionId, Long knowledgeId, boolean isPublic, Pageable pageable) {
        // 先根据 courseId 和 sectionId 和 knowledgeId 查出当前知识点所有判断题
        Page<Judgement> judgements = judgementRepository.findAllByCourseIdAndSectionIdAndKnowledgeIdAndIsPublic(courseId, sectionId, knowledgeId, isPublic, pageable);
        // 获取到分页信息中的数据
        List<Judgement> content = judgements.getContent();
        // 对数据进行遍历
        for (Judgement judgement : content) {
            // 对每个数据进行完整性填充
            completeEntity(judgement);
        }
        return judgements;
    }

    @Override
    public Page<Judgement> findByNameContainingAndIsPublic(String name, boolean isPublic, Pageable pageable) {
        // 先根据 name 查出与之匹配的判断题
        Page<Judgement> judgements = judgementRepository.findByNameContainingAndIsPublic(name, isPublic, pageable);
        // 获取到分页信息中的数据
        List<Judgement> content = judgements.getContent();
        // 对数据进行遍历
        for (Judgement judgement : content) {
            // 对每个数据进行完整性填充
            completeEntity(judgement);
        }
        return judgements;
    }
}
