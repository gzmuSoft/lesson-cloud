package cn.edu.gzmu.service;

import cn.edu.gzmu.model.entity.Paper;

import java.util.List;


/**
 * Paper Service
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
public interface PaperService extends BaseService<Paper, Long> {

    /**
     * 根据考试 id 获取当前用户的试卷信息
     *
     * @param id
     * @return 试卷
     */
    List<Paper> searchByExamId(Long id);

}
