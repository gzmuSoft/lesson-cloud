package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.*;
import cn.edu.gzmu.repository.entity.*;
import cn.edu.gzmu.service.PaperDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * PaperDetail Service Impl
 *
 * @author echo
 * @author YMS
 * @version 1.0
 * @date 2019-5-7 11:33:57
 * <p>
 * 根据 paperDetail 的 QuestionType 和 QuestionId 获取问题的信息
 * 关联查询出问题的课程，章节，知识点（编程题无此三个属性）
 * @date 2019-8-2
 */
@Service
@RequiredArgsConstructor
public class PaperDetailServiceImpl extends BaseServiceImpl<PaperDetailRepository, PaperDetail, Long>
        implements PaperDetailService {
    @Override
    protected PaperDetail completeEntity(PaperDetail paperDetail) {
        // TODO: 试卷完整性填充
        return paperDetail;
    }
}
