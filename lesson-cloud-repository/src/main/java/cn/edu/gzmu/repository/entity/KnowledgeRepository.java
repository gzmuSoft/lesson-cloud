package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.Knowledge;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Set;


/**
 * Knowledge Repository
 *
 * @author echo
 * @author ypx
 * @version 1.0
 * @date 2019-5-23 17:38:13
 */
@RepositoryRestResource(path = "knowledge")
public interface KnowledgeRepository extends BaseRepository<Knowledge, Long> {

    /**
     * 通过 section 查询
     *
     * @param id section id
     * @return 结果
     */
    @RestResource(path = "section", rel = "searchBySectionId")
    List<Knowledge> findAllBySectionId(@Param("id") Long id);

    /**
     * 通过 section ids 查询
     *
     * @param ids section ids
     * @return 结果
     */
    List<Knowledge> findAllBySectionIdIn(List<Long> ids);

    /**
     * 通过ids查询已存在的knowledge
     *
     * @param ids id list
     * @return 结果
     */
    Set<Knowledge> findDistinctByIdIn(List<Long> ids);

}