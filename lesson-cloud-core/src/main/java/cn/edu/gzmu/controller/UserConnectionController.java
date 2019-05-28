package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.UserConnection;
import cn.edu.gzmu.service.UserConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * UserConnection Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-28 10:48:37
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping("/userConnection/search")
public class UserConnectionController extends BaseController<UserConnection, UserConnectionService, Long> {
    private final static String RESOURCE = "/userConnection/search";

}
