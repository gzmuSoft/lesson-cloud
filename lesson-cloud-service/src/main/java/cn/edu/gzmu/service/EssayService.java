package cn.edu.gzmu.service;

import cn.edu.gzmu.model.entity.Essay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * Essay Service
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
public interface EssayService extends BaseService<Essay, Long> {
    /**
     * 根据课程Id查询问答题分页的数据.
     *
     * @param courseId 课程ID
     * @param pageable 分页
     * @return org.springframework.data.domain.Page<cn.edu.gzmu.model.entity.Essay>
     * @author Japoul
     * @date 2020/1/10 15:04
     */
    Page<Essay> findAllByCourseId(Long courseId, Pageable pageable);

    /**
     * 根据课程Id查询问答题列表.
     *
     * @param courseId 课程ID
     * @return
     * @author Japoul
     * @date 2020/1/10 15:04
     */
    List<Essay> findAllByCourseId(Long courseId);
}
