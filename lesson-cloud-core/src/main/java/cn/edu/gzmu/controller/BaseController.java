package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.BaseEntity;
import cn.edu.gzmu.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.BasicLinkBuilder;


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
@SuppressWarnings("all")
public class BaseController<E extends BaseEntity, S extends BaseService, ID> {
    static final String COMPLETE = "/complete";

    @Autowired
    private PagedResourcesAssembler<E> myPagedResourcesAssembler;

    /**
     * 获取分页元数据
     *
     * @param page 分页对象
     * @return 分页元数据
     * @deprecated 请使用 pagedResources 方法替代以进行获取分页资源
     */
    @Deprecated
    PagedResources.PageMetadata toPageMetadata(Page page) {
        return new PagedResources.PageMetadata(page.getSize(),
                page.getNumber(),
                page.getTotalElements(),
                page.getTotalPages());
    }

    PagedResources<Resource<E>> pagedResources(String resource, String info, Page<E> page) {
        PagedResources<Resource<E>> pagedResources = myPagedResourcesAssembler.toResource(page);
        int number = page.getNumber();
        final String path = resource + info + "?page=%s&size=%s";
        if (page.hasNext()) {
            pagedResources.add(BasicLinkBuilder.linkToCurrentMapping()
                    .slash(String.format(path, number + 1, page.getSize()))
                    .withRel(Link.REL_NEXT));
        }
        if (page.hasPrevious()) {
            pagedResources.add(BasicLinkBuilder.linkToCurrentMapping()
                    .slash(String.format(path, number - 1, page.getSize()))
                    .withRel(Link.REL_PREVIOUS));
        }
        return pagedResources;
    }

    PagedResources<Resource<E>> pagedResources(String resource, Page<E> page) {
        return pagedResources(resource, COMPLETE, page);
    }

}
