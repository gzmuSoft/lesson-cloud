package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.Knowledge;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


/**
 * Knowledge Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-23 17:38:13
 *
 * @author ypx
 */
@RepositoryRestResource(path = "knowledge")
public interface KnowledgeRepository extends BaseRepository<Knowledge, Long> {

    /**
     * 根据课程id和章节id查询所有
     *
     * @param sectionId section Id
     * @param courseId course Id
     * @return Knowledge
     */
    List<Knowledge> findAllBySectionIdAndCourseId(Long sectionId, Long courseId);

    /**
     * 通过 section 查询
     *
     * @param id id
     * @return 结果
     */
    @RestResource(path = "section", rel = "searchBySectionId")
    List<Knowledge> findAllBySectionId(@Param("id") Long id);

}