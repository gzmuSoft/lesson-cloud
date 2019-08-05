package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Section;
import cn.edu.gzmu.repository.entity.CourseRepository;
import cn.edu.gzmu.repository.entity.SectionRepository;
import cn.edu.gzmu.service.SectionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * Section Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class SectionServiceImpl extends BaseServiceImpl<SectionRepository, Section, Long>
        implements SectionService {

    private final @NonNull SectionRepository sectionRepository;
    private final @NonNull CourseRepository courseRepository;

    @Override
    protected Section completeEntity(Section entity) {
        return entity.setCourse(courseRepository.findById(entity.getCourseId()).orElse(null))
                .setParent(sectionRepository.findById(entity.getParentId()).orElse(null));
    }
}
