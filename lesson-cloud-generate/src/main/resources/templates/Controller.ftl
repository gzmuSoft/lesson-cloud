package ${package_name};

import ${entity_path};
import ${service_class}Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ${class_name} Controller
 *
 * @author echo
 * @version ${now_version}
 * @date ${.now?datetime}
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping("/${rest_path}/search")
public class ${class_name}Controller extends BaseController<${class_name}, ${class_name}Service, Long> {
    private final static String RESOURCE = "/${rest_path}/search";

}
