package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.entity.Program;
import cn.edu.gzmu.service.ProgramService;
import cn.edu.gzmu.controller.BaseController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Program Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-14 11:36:13
 */
@RepositoryRestController
@RequestMapping("/programs/search")
public class ProgramController extends BaseController<Program, ProgramService, Long> {

}
