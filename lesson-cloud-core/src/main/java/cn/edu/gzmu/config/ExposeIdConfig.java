package cn.edu.gzmu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 冫soul丶
 * @version 1.0
 * @date created in 下午5:49 19-5-24
 */
@Configuration
public class ExposeIdConfig implements RepositoryRestConfigurer {

    private final EntityManagerFactory entityManagerFactory;

    public ExposeIdConfig(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * 获取所有实体类型
     */
    private List<Class<?>> getAllManagedEntityTypes() {
        List<Class<?>> entityClasses = new ArrayList<>();
        Metamodel metamodel = entityManagerFactory.getMetamodel();
        for (ManagedType<?> managedType : metamodel.getManagedTypes()) {
            Class<?> javaType = managedType.getJavaType();
            if (javaType.isAnnotationPresent(Entity.class)) {
                entityClasses.add(managedType.getJavaType());
            }
        }
        return entityClasses;
    }

    /**
     * 暴露所有实体id
     */
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        List<Class<?>> entityClasses = getAllManagedEntityTypes();
        for (Class<?> entityClass : entityClasses) {
            config.exposeIdsFor(entityClass);
        }
    }


}
