package cn.edu.gzmu.service;

import cn.edu.gzmu.model.entity.Knowledge;

import java.util.List;

/**
 * Knowledge Service
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 *
 * @author ypx
 */
public interface KnowledgeService extends BaseService<Knowledge, Long> {

    /**
     * 根据课程id和章节id查询知识点
     * @param sectionId
     * @param courseId
     * @return
     */
    List<Knowledge> searchBySectionIdAndCourseId(Long sectionId,Long courseId);
}
