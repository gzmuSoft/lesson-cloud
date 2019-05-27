package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.BaseEntity;
import cn.edu.gzmu.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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
public class BaseController<E extends BaseEntity, S extends BaseService, ID> {
    static final String COMPLETE = "/complete";

    PagedResources.PageMetadata toPageMetadata(Page page) {
        return new PagedResources.PageMetadata(page.getSize(),
                page.getNumber(),
                page.getTotalElements(),
                page.getTotalPages());
    }


}
