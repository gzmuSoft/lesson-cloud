package cn.edu.gzmu.repository.entity;


import cn.edu.gzmu.model.constant.SectionType;
import cn.edu.gzmu.model.entity.Section;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

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
     * @param id CourseId
     * @return 结果
     */
    @RestResource(path = "course", rel = "byCourseId")
    List<Section> findAllByCourseId(@Param("id") Long id);

    /**
     * 更具课程 id 和类型查询
     *
     * @param courseId 课程 id
     * @param type     类型
     * @return 结果
     */
    @RestResource(path = "/courseAndType", rel = "byCourseIdAndType")
    List<Section> findAllByCourseIdAndType(@Param("courseId") Long courseId, @Param("type") SectionType type);

    /**
     * 根据章id 查询所有的节
     *
     * @param id 章 id
     * @return 结果
     */
    @RestResource(path = "/passage", rel = "byPassage")
    List<Section> findAllByParentId(@Param("passageId") Long id);

    /**
     * 根据章ids 查询所有的节
     *
     * @param ids 章ids
     * @return 结果
     */
    List<Section> findAllByParentIdIn(List<Long> ids);
}