package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Paper;
import cn.edu.gzmu.repository.entity.ExamRepository;
import cn.edu.gzmu.repository.entity.PaperRepository;
import cn.edu.gzmu.service.PaperService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Paper Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class PaperServiceImpl extends BaseServiceImpl<PaperRepository, Paper, Long>
        implements PaperService {
    private final @NonNull ExamRepository examRepository;

    @Override
    public Page<Paper> searchAll(Pageable pageable) {
        return super.searchAll(pageable).map(paper -> {
            if (paper.getExamId() != null) {
                paper.setExam(examRepository.getOne(paper.getExamId()));
            }
            return paper;
        });
    }

}
