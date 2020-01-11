package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Program;
import cn.edu.gzmu.repository.entity.CourseRepository;
import cn.edu.gzmu.repository.entity.KnowledgeRepository;
import cn.edu.gzmu.repository.entity.ProgramRepository;
import cn.edu.gzmu.repository.entity.SectionRepository;
import cn.edu.gzmu.service.ProgramService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Program Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class ProgramServiceImpl extends BaseServiceImpl<ProgramRepository, Program, Long>
        implements ProgramService {
    private final @NonNull ProgramRepository programRepository;
    private final @NonNull CourseRepository courseRepository;
    private final @NonNull SectionRepository sectionRepository;
    private final @NonNull KnowledgeRepository knowledgeRepository;

    @Override
    protected Program completeEntity(Program entity) {
        return entity
                .setCourse(courseRepository.findById(entity.getCourseId()).orElse(null))
                .setSection(sectionRepository.findById(entity.getSectionId()).orElse(null))
                .setKnowledge(knowledgeRepository.findById(entity.getKnowledgeId()).orElse(null));
    }

    @Override
    public Page<Program> findAllByCourseId(Long courseId, Pageable pageable) {
        // 先根据 courseId 查出当前课程所有编程题
        Page<Program> programs = programRepository.findAllByCourseId(courseId, pageable);
        // 获取到分页信息中的数据
        List<Program> content = programs.getContent();
        // 对数据进行遍历
        for (Program program : content) {
            // 对每个数据进行完整性填充
            completeEntity(program);
        }
        return programs;
    }

    @Override
    public Page<Program> findAllByCourseIdAndSectionId(Long courseId, Long sectionId, Pageable pageable) {
        // 先根据 courseId 和 sectionId 查出当前课程所有编程题
        Page<Program> programs = programRepository.findAllByCourseIdAndSectionId(courseId, sectionId, pageable);
        // 获取到分页信息中的数据
        List<Program> content = programs.getContent();
        // 对数据进行遍历
        for (Program program : content) {
            // 对每个数据进行完整性填充
            completeEntity(program);
        }
        return programs;
    }

    @Override
    public Page<Program> findAllByCourseIdAndSectionIdAndKnowledgeId(Long courseId, Long sectionId, Long knowledgeId, Pageable pageable) {
        // 先根据 courseId 和 sectionId 和 knowledgeId 查出当前课程所有编程题
        Page<Program> programs = programRepository.findAllByCourseIdAndSectionIdAndKnowledgeId(courseId, sectionId, knowledgeId, pageable);
        // 获取到分页信息中的数据
        List<Program> content = programs.getContent();
        // 对数据进行遍历
        for (Program program : content) {
            // 对每个数据进行完整性填充
            completeEntity(program);
        }
        return programs;
    }

    @Override
    public Page<Program> findByNameContaining(String name, Pageable pageable) {
        // 先根据 name 查出当前课程所有编程题
        Page<Program> programs = programRepository.findByNameContaining(name, pageable);
        // 获取到分页信息中的数据
        List<Program> content = programs.getContent();
        // 对数据进行遍历
        for (Program program : content) {
            // 对每个数据进行完整性填充
            completeEntity(program);
        }
        return programs;
    }
}