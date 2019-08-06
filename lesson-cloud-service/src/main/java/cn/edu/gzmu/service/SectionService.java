package cn.edu.gzmu.service;

import cn.edu.gzmu.model.entity.Section;

import java.util.List;


/**
 * Section Service
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
public interface SectionService extends BaseService<Section, Long> {
    /**
     * 根据课程 id 获取章节信息
     *
     * @param id id
     * @return 章节信息
     */
    List<Section> searchByCourseId(Long id);



}
