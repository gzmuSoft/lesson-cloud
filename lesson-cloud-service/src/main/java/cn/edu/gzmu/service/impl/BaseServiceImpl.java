package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.BaseEntity;
import cn.edu.gzmu.model.exception.ResourceNotFoundException;
import cn.edu.gzmu.repository.base.BaseRepository;
import cn.edu.gzmu.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * BaseService
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Slf4j
@SuppressWarnings({"all", "unchecked"})
public abstract class BaseServiceImpl<R extends BaseRepository<T, ID>, T extends BaseEntity, ID>
        implements BaseService<T, ID> {

    private Class<? extends BaseEntity> entityClass;

    @Autowired
    private R baseRepository;

    @Autowired
    private Map<String, BaseRepository> repositoryMap;

    @Override
    public Page<T> searchAll(Pageable pageable) {
        return baseRepository.findAll(pageable).map(this::completeEntity);
    }

    @Override
    public T searchById(ID id) {
        return completeEntity(baseRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    /**
     * 实体的封装
     * 对于子类来说，不同的仅仅是他的实体类的封装方式，我们需要子类自己去定义他的实现
     * 通过他的实现来完成收集以及封装操作
     *
     * @param entity 实体
     * @return 封装完整信息的实体
     */
    protected T completeEntity(T entity) {
        return entity;
    };
//    protected T completeEntity(T entity) {
//        entityClass = entity.getClass();
//        List<Field> fields = Arrays.stream(entityClass.getDeclaredFields())
//                .filter(field -> field.isAnnotationPresent(Transient.class))
//                .collect(Collectors.toList());
//        if (fields.size() == 0) {
//            return entity;
//        }
//        fields.forEach(field -> fieldHandle(entity, field));
//        return entity;
//    }

    /**
     * 字段处理
     *
     * @param entity 实体类
     * @param field  字段
     */
//    private void fieldHandle(T entity, Field field) {
//        try {
//            Class<?> fieldType = field.getType();
//            Method setMethod = entityClass.getMethod("set" + WordUtils.capitalize(field.getName()), fieldType);
//            if (fieldType.isAssignableFrom(List.class)) {
//                FieldRepository fieldRepository = field.getAnnotation(FieldRepository.class);
//                BaseRepository repository = fieldRepository == null
//                        ? repositoryMap.get(field.getName() + "Repository")
//                        : repositoryMap.get(fieldRepository.value());
//                Method getIdsMethod = entityClass.getMethod("get" + WordUtils.capitalize(field.getName()) + "Ids");
//                Object getIds = getIdsMethod.invoke(entity);
//                if (getIds == null) {
//                    return;
//                }
//                List<String> ids = Splitter.on(",").trimResults().splitToList(getIds.toString());
//                setMethod.invoke(entity, repository.searchAllByIds(ids));
//                return;
//            }
//            BaseRepository repository = repositoryMap.get(WordUtils.uncapitalize(fieldType.getSimpleName()) + "Repository");
//            Method getIdMethod = entityClass.getMethod("get" + WordUtils.capitalize(field.getName()) + "Id");
//            setMethod.invoke(entity, repository.getOne(getIdMethod.invoke(entity)));
//        } catch (NoSuchMethodException | NullPointerException e) {
//            log.debug(e.getMessage());
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            log.error(e.getMessage());
//            throw new ResourceException();
//        }
//    }
}
