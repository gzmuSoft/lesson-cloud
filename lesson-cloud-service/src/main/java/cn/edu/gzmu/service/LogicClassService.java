package cn.edu.gzmu.service;

import cn.edu.gzmu.model.entity.LogicClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * LogicClass Service
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-20 9:18:57
 */
public interface LogicClassService extends BaseService<LogicClass, Long> {

    /**
     * 查询所有逻辑班级分页信息
     *
     * @param pageable 分页
     * @return 分页结果
     */
    Page<LogicClass> searchAll(Pageable pageable);
}
