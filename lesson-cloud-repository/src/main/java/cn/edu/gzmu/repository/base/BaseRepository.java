package cn.edu.gzmu.repository.base;

import cn.edu.gzmu.model.BaseEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 基类
 *
 * @param <T>  实体类
 * @param <ID> 主键类型
 * @author echo
 * @version 1.0
 * @date 2019-4-11 11:59:46
 */
@NoRepositoryBean
@SuppressWarnings({"all", "uncheck"})
public interface BaseRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    /**
     * 查询所有数据
     *
     * @param pageable 分页
     * @return 结果
     */
    @RestResource(exported = false)
    @Query(value = "select * from #{#entityName} ", countQuery = "select count(*) from #{#entityName}", nativeQuery = true)
    Page<T> findAllExist(Pageable pageable);

    /**
     * 查询所有数据
     *
     * @return 结果
     */
    @RestResource(path = "all", rel = "all")
    @Query(value = "select * from #{#entityName} where is_enable = 1", nativeQuery = true)
    List<T> searchAll();

    /**
     * 通过 id 列表查询
     *
     * @param ids id 列表
     * @return 结果
     */
    @RestResource(exported = false)
    @Query(value = "select * from #{#entityName}  where id in (:ids) and is_enable = 1 ", nativeQuery = true)
    List<T> searchAllByIds(@Param("ids") List<ID> ids);

    /**
     * 通过 id 列表查询
     *
     * @param ids id 列表
     * @param pageable page
     * @return 结果
     */
    @RestResource(exported = false)
    @Query(value = "select * from #{#entityName}  where id in (:ids) and is_enable = 1 ",
            countQuery = "select count(*) from #{#entityName}", nativeQuery = true)
    Page<T> searchAllByIds(@Param("ids") List<ID> ids, Pageable pageable);

    /**
     * 真正删除一个数据
     *
     * @param id id
     */
    @Modifying
    @RestResource(exported = false)
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "delete from #{#entityName} where id = :id", nativeQuery = true)
    void deleteExistById(@Param("id") ID id);


    /**
     * 禁用默认的删除方法
     *
     * @param id id
     */
    @Override
    default void deleteById(@NotNull ID id) {
        deleteExistById(id);
    }

    /**
     * 根据名称模糊
     *
     * @param name
     * @return org.springframework.data.domain.Page<T>
     * @author Soul
     * @date 2020/1/17 21:43
     */
    @RestResource(path = "name", rel = "byNameContaining")
    List<T> findByNameContaining(@Param("containing") String name);

    /**
     * 根据名称模糊
     *
     * @param name 名字
     * @param pageable 分页
     */
    @RestResource(path = "nameAndPage", rel = "byNameContainingPage")
    Page<T> findByNameContaining(@Param("containing") String name, Pageable pageable);
}
