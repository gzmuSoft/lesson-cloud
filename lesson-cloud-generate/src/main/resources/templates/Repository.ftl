package ${package_name};

import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * ${table_name} Repository
 *
 * @author echo
 * @version ${now_version}
 * @date ${.now?datetime}
 */
@RepositoryRestResource
public interface ${class_name}Repository extends ${base_repository}<${entity_class}, Long> {
}