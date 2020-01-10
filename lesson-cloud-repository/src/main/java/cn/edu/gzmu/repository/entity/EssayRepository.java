package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.Essay;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


/**
 * Essay Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-23 17:38:13
 */
@RepositoryRestResource(path = "essay")
public interface EssayRepository extends BaseRepository<Essay, Long> {

    /**
     * 根据 courseId 查询分页内容
     *
     * @param courseId 课程ID
     * @param pageable 分页类
     * @return 结果
     */
    Page<Essay> findAllByCourseId(Long courseId, Pageable pageable);

    /**
     * 根据 courseId 查询分页内容
     *
     * @param courseId 课程ID
     * @return 结果
     */
    List<Essay> findAllByCourseId(Long courseId);
}