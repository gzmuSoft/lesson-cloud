package cn.edu.gzmu.service;

import cn.edu.gzmu.model.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * BaseService
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
public interface BaseService<T extends BaseEntity, ID> {

    /**
     * 查询所有学生分页信息
     *
     * @param pageable 分页
     * @return 分页结果
     */
    Page<T> searchAll(Pageable pageable);

    /**
     * 根据 ID 查询
     *
     * @param id id
     * @return 结果
     */
    T searchById(ID id);
}
