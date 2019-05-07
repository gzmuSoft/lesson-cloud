package ${package_name}.impl;

import ${entity_path};
import ${repository_class}Repository;
import ${package_name}.${class_name}Service;
import org.springframework.stereotype.Service;


/**
* ${class_name} Service Impl
*
* @author echo
* @version ${now_version}
* @date ${.now?datetime}
*/
@Service
public class ${class_name}ServiceImpl extends BaseServiceImpl<${class_name}Repository, ${class_name}, Long>
        implements ${class_name}Service {

}
