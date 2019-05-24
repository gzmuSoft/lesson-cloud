package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.UserConnection;
import cn.edu.gzmu.service.UserConnectionService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * UserConnection Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-24 14:18:22
 */
@RepositoryRestController
@RequestMapping("/userConnection/search")
public class UserConnectionController extends BaseController<UserConnection, UserConnectionService, Long> {

}
