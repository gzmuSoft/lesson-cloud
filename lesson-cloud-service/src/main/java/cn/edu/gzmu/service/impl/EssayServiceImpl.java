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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


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
    private final @NonNull EssayRepository essayRepository;

    @Override
    protected Essay completeEntity(Essay entity) {
        return entity
                .setCourse(courseRepository.findById(entity.getCourseId()).orElse(null))
                .setKnowledge(knowledgeRepository.findById(entity.getKnowledgeId()).orElse(null))
                .setSection(sectionRepository.findById(entity.getSectionId()).orElse(null));

    }

    @Override
    public Page<Essay> findAllByCourseId(Long courseId, Pageable pageable) {
        // 先根据courseId查出当前课程所有问答题并分页
        Page<Essay> essays = essayRepository.findAllByCourseId(courseId, pageable);
        // 获取到分页信息中的数据
        List<Essay> content = essays.getContent();
        // 对数据进行遍历
        for (Essay element : content) {
            // 对每个数据进行完整性填充
            completeEntity(element);
        }
        return essays;
    }

    @Override
    public List<Essay> findAllByCourseId(Long courseId) {
        List<Essay> essayList = essayRepository.findAllByCourseId(courseId);
        // 对数据进行遍历
        for (Essay essay : essayList) {
            // 对每个数据进行完整性填充
            completeEntity(essay);
        }
        return essayList;
    }
}