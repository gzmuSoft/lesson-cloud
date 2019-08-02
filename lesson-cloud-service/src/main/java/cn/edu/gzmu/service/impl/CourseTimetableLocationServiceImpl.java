package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.CourseTimetableLocation;
import cn.edu.gzmu.model.exception.ResourceNotFoundException;
import cn.edu.gzmu.repository.entity.CourseTimetableLocationRepository;
import cn.edu.gzmu.repository.entity.LogicClassRepository;
import cn.edu.gzmu.service.CourseTimetableLocationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * CourseTimetableLocation Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-20 9:18:57
 */
@Service
@RequiredArgsConstructor
public class CourseTimetableLocationServiceImpl extends BaseServiceImpl<CourseTimetableLocationRepository, CourseTimetableLocation, Long>
        implements CourseTimetableLocationService {
    private final @NonNull LogicClassRepository logicClassRepository;

    @Override
    protected CourseTimetableLocation completeEntity(CourseTimetableLocation entity) {
        return entity.setLogicClass(
                logicClassRepository.findById(entity.getLogicClassId()).orElseThrow(
                        () -> new ResourceNotFoundException("Logic class can not be find!")
                )
        );
    }
}
