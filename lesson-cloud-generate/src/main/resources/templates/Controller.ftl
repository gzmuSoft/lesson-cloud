package ${package_name};

import ${entity_path};
import ${repository_class}Repository;
import ${base_controller};
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* ${class_name} Controller
*
* @author echo
* @version ${now_version}
* @date ${.now?datetime}
*/
@RepositoryRestController
@RequestMapping("/${rest_path}")
public class ${class_name}Controller extends BaseController<${class_name}, ${class_name}Repository, Long> {

}
