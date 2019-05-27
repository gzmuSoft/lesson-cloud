package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.SysUser;
import cn.edu.gzmu.service.SysUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SysUser Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-24 14:18:22
 */
@RepositoryRestController
@RequestMapping("/sysUser/search")
public class SysUserController extends BaseController<SysUser, SysUserService, Long> {
    private final static String RESOURCE = "/sysUser/search/";
    private final SysUserService sysUserService;

    public SysUserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @GetMapping(COMPLETE)
    public HttpEntity<?> resources(@PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        Page<SysUser> page = sysUserService.searchAll(pageable);
        return ResponseEntity.ok(new PagedResources<>(page.getContent(), toPageMetadata(page),
                ControllerLinkBuilder.linkTo(SysUserController.class).withSelfRel()));
    }

}
