package ${package_name};

import ${entity_path};
import ${base_repository};
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * ${class_name} Repository
 *
 * @author echo
 * @version ${now_version}
 * @date ${.now?datetime}
 */
@RepositoryRestResource
public interface ${class_name}Repository extends BaseRepository<${class_name}, Long> {

}