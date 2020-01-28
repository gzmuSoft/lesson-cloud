package cn.edu.gzmu.repository.entity;


import cn.edu.gzmu.model.entity.Section;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Set;


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
     * 根据 courseId 查询所有
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
     * @param parentId 父(章) id
     * @return 结果
     */
    @RestResource(path = "courseAndPassage", rel = "byCourseAndPassage")
    List<Section> findAllByCourseIdAndParentId(@Param("courseId") Long courseId, @Param("passageId") Long parentId);

    /**
     * 根据 章id 查询所有的节
     *
     * @param id 章 id
     * @return 结果
     */
    @RestResource(path = "passage", rel = "byPassage")
    List<Section> findAllByParentId(@Param("passageId") Long id);

    /**
     * 根据章ids 查询所有的节
     *
     * @param ids 章ids
     * @return 结果
     */
    List<Section> findAllByParentIdIn(List<Long> ids);

    /**
     * 查询section
     *
     * @param ids      id list
     * @param parentId 父章节 =0
     * @return 结果
     */
    Set<Section> findDistinctByIdInAndParentId(List<Long> ids, Long parentId);

    /**
     * 查询section
     *
     * @param ids      id list
     * @param parentId 父章节 !=0
     * @return 结果
     */
    Set<Section> findDistinctByIdInAndParentIdNot(List<Long> ids, Long parentId);

    /**
     * 根据 ids 查询 section
     *
     * @param ids 章节 id 列表
     * @return 章节列表
     */
    Set<Section> findDistinctByIdIn(List<Long> ids);
}