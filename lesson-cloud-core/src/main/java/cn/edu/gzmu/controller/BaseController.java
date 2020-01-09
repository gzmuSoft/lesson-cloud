package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.BaseEntity;
import cn.edu.gzmu.service.BaseService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;


/**
 * 控制器基类
 *
 * @param <E>  实体类
 * @param <S>  资源
 * @param <ID> id
 * @author echo
 * @version 1.0
 * @date 2019-4-11 14:50:46
 */
@SuppressWarnings({"all", "unchecked"})
public abstract class BaseController<E extends BaseEntity, S extends BaseService<E, ID>, ID> {
    private static final String COMPLETE = "/complete";
    private static final String COMPLETE_ONE = COMPLETE + "/{id}";
    private static final String SAVEALL = "/saveAll";
    private static final String REPOSITORY_PACKAGE = "cn.edu.gzmu.repository.entity.";

    @Autowired
    private S baseService;

    @Autowired
    private RepositoryEntityLinks repositoryEntityLinks;

    @Autowired
    private PagedResourcesAssembler<E> myPagedResourcesAssembler;

    /**
     * 保存资源列表.
     * @param data
     * @return
     */
    @PostMapping(SAVEALL)
    public HttpEntity<?> saveAll(@NotNull @RequestBody List<E> entityList){
        if(Objects.isNull(entityList)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        baseService.saveAll(entityList);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 获取完整的分页资源
     *
     * @param pageable 分页
     * @return 结果
     */
    @GetMapping(COMPLETE)
    public HttpEntity<?> resources(@PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        return ResponseEntity.ok(baseService.searchAll(pageable));
    }

    /**
     * 获取单个的分页资源
     *
     * @param id id
     * @return 结果
     */
    @GetMapping(COMPLETE_ONE)
    public HttpEntity<?> resource(@PathVariable ID id) {
        return ResponseEntity.ok(baseService.searchById(id));
    }

    private Class getRepository() {
        try {
            return Class.forName(REPOSITORY_PACKAGE + StringUtils.substringBefore(getClass().getSimpleName(),
                    "Controller") + "Repository");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
