package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Semester;
import cn.edu.gzmu.repository.auth.SemesterRepository;
import cn.edu.gzmu.service.SemesterService;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


/**
 * Semester Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class SemesterServiceImpl implements SemesterService {

    private final SemesterRepository semesterRepository;

    @Override
    public List<Semester> searchAll() {
        JSONObject embedded = semesterRepository.searchAll().getJSONObject("_embedded");
        return embedded.containsKey("semesters")
                ? embedded.getJSONArray("semesters").toJavaList(Semester.class)
                : Collections.emptyList();
    }
}
