package cn.edu.gzmu.repository.entity;


import cn.edu.gzmu.model.entity.Section;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


/**
 * Section Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-23 17:38:13
 */
@RepositoryRestResource(path = "section")
public interface SectionRepository extends BaseRepository<Section, Long> {
    /**
     * 根据 exam id 查询所有
     *
     * @param id ClassId
     * @return 结果
     */
    List<Section> findAllByCourseId(Long id);
}