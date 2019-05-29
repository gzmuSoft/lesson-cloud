package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.BaseEntity;
import cn.edu.gzmu.model.exception.ResourceNotFoundException;
import cn.edu.gzmu.repository.BaseRepository;
import cn.edu.gzmu.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * BaseService
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@SuppressWarnings({"all", "unchecked"})
public abstract class BaseServiceImpl<R extends BaseRepository<T, ID>, T extends BaseEntity, ID> implements BaseService<T, ID> {

    @Autowired
    private R baseRepository;

    @Override
    public Page<T> searchAll(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }

    @Override
    public T searchById(ID id) {
        return baseRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("the resource by id %s not found!", id))
        );
    }

}
