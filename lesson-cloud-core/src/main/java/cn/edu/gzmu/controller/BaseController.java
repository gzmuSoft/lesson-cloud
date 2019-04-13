package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.BaseEntity;
import cn.edu.gzmu.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制器基类
 *
 * @param <E> 实体类
 * @param <R> 资源
 * @param <ID> id
 * @author echo
 * @version 1.0
 * @date 2019-4-11 14:50:46
 */
public class BaseController<E extends BaseEntity, R extends BaseRepository, ID> {
//
//    @Autowired
//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
//    private R baseRepository;
//
//    /**
//     * 真正的删除资源
//     *
//     * @param id id
//     * @return 结果
//     */
//    @SuppressWarnings("unchecked")
//    @DeleteMapping("/search/{/id}")
//    @ResponseBody
//    public HttpEntity<?> deleteResource(@PathVariable ID id) {
//        baseRepository.deleteExistById(id);
//        return ResponseEntity.noContent().build();
//    }

}
