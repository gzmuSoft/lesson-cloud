package cn.edu.gzmu.service;

import cn.edu.gzmu.model.entity.Semester;

import java.util.List;


/**
 * Semester Service
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
public interface SemesterService {

    /**
     * 获取所有
     *
     * @return 结果
     */
    List<Semester> searchAll();

}
