package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Exam;
import cn.edu.gzmu.repository.entity.CourseRepository;
import cn.edu.gzmu.repository.entity.ExamRepository;
import cn.edu.gzmu.repository.entity.LogicClassRepository;
import cn.edu.gzmu.service.ExamService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Exam Service Impl
 *
 * @author echo
 * @author Japoul
 * @version 1.0
 * @date 2019-5-7 11:33:57
 * <p>
 * 根据课程id和逻辑班级id列表查询考试信息
 * @date 2019-8-09 15:38:13
 */
@Service
@RequiredArgsConstructor
public class ExamServiceImpl extends BaseServiceImpl<ExamRepository, Exam, Long>
        implements ExamService {
    private final @NonNull ExamRepository examRepository;
    private final @NonNull CourseRepository courseRepository;
    private final @NonNull LogicClassRepository logicClassRepository;

    @Override
    protected Exam completeEntity(Exam entity) {

        return entity
                .setCourse(courseRepository.findById(entity.getCourseId()).orElse(null));
    }

    @Override
    public Page<Exam> searchByClassAndCourse(String courseId, String logicClassIds, Pageable pageable) {
        // 用 courseid 获取所有符合的考试信息
        List<Exam> listCourse = examRepository.findAllByCourseId(Long.parseLong(courseId));
        // 遍历 list 获取 id 和 logicClassIds 存入一个 map
        HashMap<Long, String> map = new HashMap<>();
        for (Exam e : listCourse) {
            map.put(e.getId(), e.getLogicClassIds());
        }
        // 遍历map获取 value 并使用逗号分割 logicClassIds，
        // 将分割后的数组与前台传过来并同样分割好的数组进行比对得到匹配的数据的id列表
        String[] requestIds = logicClassIds.split(",");
        String[] classIds;
        List ids = new ArrayList();
        int count = 0;
        for (Map.Entry<Long, String> entry : map.entrySet()) {
            classIds = entry.getValue().split(",");
            for (String logicClassId : classIds) {
                if (requestIds[count].equals(logicClassId)) {
                    count++;
                }
                if (count == requestIds.length) {
                    ids.add(entry.getKey());
                    count = 0;
                    break;
                }
            }
        }

        // 获取根据条件查询到的page
        Page<Exam> page = examRepository.findAllByIdIsIn(ids, pageable);
        // 获取列表
        List<Exam> content = page.getContent();
        // 遍历
        for (Exam element : content) {
            // 对每个数据进行完整性填充
            completeEntity(element);
        }
        return page;
    }
}
