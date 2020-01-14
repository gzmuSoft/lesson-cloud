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
    public Page<Program> findAllByIsPublic(boolean isPublic, Pageable pageable) {
        // 先根据 isPublic 查出当前课程所有编程题
        Page<Program> programs = programRepository.findAllByIsPublic(isPublic, pageable);
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
    public Page<Program> findAllByCourseIdAndIsPublic(Long courseId, boolean isPublic, Pageable pageable) {
        // 先根据 courseId 查出当前课程所有编程题
        Page<Program> programs = programRepository.findAllByCourseIdAndIsPublic(courseId, isPublic, pageable);
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
    public Page<Program> findAllByCourseIdAndSectionIdAndIsPublic(Long courseId, Long sectionId, boolean isPublic, Pageable pageable) {
        // 先根据 courseId 和 sectionId 查出当前课程所有编程题
        Page<Program> programs = programRepository.findAllByCourseIdAndSectionIdAndIsPublic(courseId, sectionId, isPublic, pageable);
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
    public Page<Program> findAllByCourseIdAndSectionIdAndKnowledgeIdAndIsPublic(Long courseId, Long sectionId, Long knowledgeId, boolean isPublic, Pageable pageable) {
        // 先根据 courseId 和 sectionId 和 knowledgeId 查出当前课程所有编程题
        Page<Program> programs = programRepository.findAllByCourseIdAndSectionIdAndKnowledgeIdAndIsPublic(courseId, sectionId, knowledgeId, isPublic, pageable);
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
    public Page<Program> findByNameContainingAndIsPublic(String name, boolean isPublic, Pageable pageable) {
        // 先根据 name 查出当前课程所有编程题
        Page<Program> programs = programRepository.findByNameContainingAndIsPublic(name, isPublic, pageable);
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