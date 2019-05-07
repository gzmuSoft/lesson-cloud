package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.BaseEntity;
import cn.edu.gzmu.repository.BaseRepository;
import cn.edu.gzmu.service.BaseService;

public class BaseServiceImpl<R extends BaseRepository, T extends BaseEntity, ID> implements BaseService<T, ID> {

}
