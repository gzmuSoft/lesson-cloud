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

import java.util.List;


/**
 * SingleSel Service Impl
 *
 * @author echo
 * @author Japoul
 * @version 1.0
 * @date 2019-5-7 11:33:57
 * <p>
 * 单项选择题联查课程,章节,知识点
 * @date 2019-8-4 23:33:57
 */
@Service
@RequiredArgsConstructor
public class SingleSelServiceImpl extends BaseServiceImpl<SingleSelRepository, SingleSel, Long>
        implements SingleSelService {

    private final @NonNull SingleSelRepository singleSelRepository;
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

    @Override
    public Page<SingleSel> findAllByCourseId(Long courseId, Pageable pageable) {
        // 先根据 courseId 查出当前课程所有单选题
        Page<SingleSel> singleSelList = singleSelRepository.findAllByCourseId(courseId, pageable);
        // 获取到分页信息中的数据
        List<SingleSel> content = singleSelList.getContent();
        // 对数据进行遍历
        for (SingleSel singleSel : content) {
            // 对每个数据进行完整性填充
            completeEntity(singleSel);
        }
        return singleSelList;
    }

    @Override
    public Page<SingleSel> findAllByCourseIdAndSectionId(Long courseId, Long sectionId, Pageable pageable) {
        // 先根据 courseId 和 sectionId 查出当前课程所有单选题
        Page<SingleSel> singleSelList = singleSelRepository.findAllByCourseIdAndSectionId(courseId, sectionId, pageable);
        // 获取到分页信息中的数据
        List<SingleSel> content = singleSelList.getContent();
        // 对数据进行遍历
        for (SingleSel singleSel : content) {
            // 对每个数据进行完整性填充
            completeEntity(singleSel);
        }
        return singleSelList;
    }

    @Override
    public Page<SingleSel> findAllByCourseIdAndSectionIdAndKnowledgeId(Long courseId, Long sectionId, Long knowledgeId, Pageable pageable) {
        // 先根据 courseId 和 sectionId 和 knowledgeId 查出当前课程所有单选题
        Page<SingleSel> singleSelList = singleSelRepository.findAllByCourseIdAndSectionIdAndKnowledgeId(courseId, sectionId, knowledgeId, pageable);
        // 获取到分页信息中的数据
        List<SingleSel> content = singleSelList.getContent();
        // 对数据进行遍历
        for (SingleSel singleSel : content) {
            // 对每个数据进行完整性填充
            completeEntity(singleSel);
        }
        return singleSelList;
    }

    @Override
    public Page<SingleSel> findByNameContaining(String name, Pageable pageable) {
        // 先根据 name 查出当前课程所有单选题
        Page<SingleSel> singleSelList = singleSelRepository.findByNameContaining(name, pageable);
        // 获取到分页信息中的数据
        List<SingleSel> content = singleSelList.getContent();
        // 对数据进行遍历
        for (SingleSel singleSel : content) {
            // 对每个数据进行完整性填充
            completeEntity(singleSel);
        }
        return singleSelList;
    }
}
