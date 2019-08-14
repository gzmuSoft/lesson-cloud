package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.dto.ExamDetailsDto;
import cn.edu.gzmu.model.entity.*;
import cn.edu.gzmu.model.exception.ResourceNotFoundException;
import cn.edu.gzmu.repository.entity.*;
import cn.edu.gzmu.service.ExamService;
import com.google.common.collect.Sets;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Exam Service Impl
 *
 * @author echo
 * @author Japoul
 * @author ljq
 * @version 1.0
 * @date 2019-5-7 11:33:57
 * <p>
 * 根据课程id和逻辑班级id列表查询考试信息
 * @date 2019-8-09 15:38:13
 *
 * <p>
 */
@Service
@RequiredArgsConstructor
public class ExamServiceImpl extends BaseServiceImpl<ExamRepository, Exam, Long>
        implements ExamService {
    private final @NonNull ExamRepository examRepository;
    private final @NonNull ExamRuleRepository examRuleRepository;
    private final @NonNull ExamHistoryRepository examHistoryRepository;
    private final @NonNull CourseRepository courseRepository;
    private final @NonNull LogicClassRepository logicClassRepository;

    @Override
    protected Exam completeEntity(Exam entity) {
        return entity
                .setCourse(courseRepository.findById(entity.getCourseId()).orElse(null));
    }

    @Override
    public Page<Exam> searchByClassAndCourse(String courseId, String logicClassIds, Pageable pageable) {
        // 用 courseId 获取所有符合的考试信息
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
        List<Long> ids = new ArrayList<Long>();
        // int count = 0;
        for (Map.Entry<Long, String> entry : map.entrySet()) {
            classIds = entry.getValue().split(",");
            for (String logicClassId : classIds) {
                //其中一个班级存在即可
                int flag = 0;
                for (String requestId : requestIds) {
                    if (requestId.equals(logicClassId)) {
                        ids.add(entry.getKey());
                        //标识此条数据正确
                        flag = 1;
                        break;
                    }
                }
                // 判定此条数据正确,直接结束当前数据班级信息的循环
                if (flag == 1) {
                    break;
                }

                /** 所有班级都存在才可
                 *
                 *   if (requestIds[count].equals(logicClassId)) {
                 *
                 *   }
                 *   if (count == requestIds.length) {
                 *       ids.add(entry.getKey());
                 *      count = 0;
                 *       break;
                 *   }
                 */
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


    @Override
    public Page<Exam> searchByStudentPage(Student student, Pageable pageable) {
        //获取当前学生的逻辑班级
        Set<LogicClass> logicClassesByClassesId = logicClassRepository.findDistinctByClassesId(student.getClassesId());
        Set<LogicClass> logicClassesByStudentId = logicClassRepository.findDistinctByStudentId(student.getId());
        Sets.SetView<LogicClass> logicClasses = Sets.union(logicClassesByClassesId, logicClassesByStudentId);
        List<Long> logicClassesIds = logicClasses.stream()
                .map(LogicClass::getId)
                .collect(Collectors.toList());
        //根据当前学生所在的逻辑班级查询当前学生的全部考试信息
        Page<Exam> exam = examRepository.findAllByLogicClassIds(logicClassesIds, pageable);
        List<Exam> content = exam.getContent();
        for (Exam element : content) {
            completeEntity(element);
        }
        return exam;
    }

    /**
     * 1. 先根据考试id查询考试信息，在通过当前登录用户得出用户 id
     * 2. 通过考试id查询组卷规则得出所有题目信息，并通过每题分值字段计算所有总分，
     * 得出当前考试总分数、题目数量。注意，不可将整个考试规则返回给前端
     * 3. 再通过考试 id 和学生 id 查询考试历史记录，得出是否已经参加考试，如果参加，
     * 得出他的最高得分和考试次数
     */
    @Override
    public ExamDetailsDto searchDetailsById(Student student, Long id) {
        // 1. 根据考试id查询考试信息
        Exam exam = examRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Exam can not be found!")
        );
        // 2. 通过考试id查询组卷规则得出所有题目信息
        List<ExamRule> examRules = examRuleRepository.findAllByExamId(exam.getId());
        // 通过每题分值字段计算所有总分,得出当前考试总分数
        double allScore = examRules.stream()
                .mapToDouble(rule -> rule.getQuestionCount() * rule.getEachValue())
                .sum();
        // 题目数量
        int count = examRules.stream().mapToInt(ExamRule::getQuestionCount).sum();
        // 3. 再通过考试 id 和学生 id 查询考试历史记录，得出是否已经参加考试
        ExamHistory examHistory = examHistoryRepository.findFirstByExamIdAndStudentId(id, student.getId()).orElse(null);
        return ExamDetailsDto.builder().exam(exam).allScore(allScore)
                .count(count).examHistory(examHistory).build();
    }

    @Override
    public Page<Exam> examFromPublish(String courseIds,String logicClassIds,Pageable pageable) {
        System.out.println(courseIds+"~~~~"+logicClassIds);
        Page<Exam> page = null;
        //在单个courseId基础上循环遍历所有的courseId
        //声明一个集合存储符合要求Eaxm
        List<Exam> examList =new ArrayList<>();
        List<Long> ids = new ArrayList<Long>();//查询id集合
        if(courseIds.equals("")&&logicClassIds.equals("")){
            page=examRepository.findAllexam(pageable);
            return page;
        }
        else {
            String[] scourseIds = courseIds.split(",");
            for (String courseId : scourseIds) {
                List<Exam> listCourse = examRepository.findAllByCourseId(Long.parseLong(courseId));
                // 遍历 list 获取 id 和 logicClassIds 存入一个 map
                HashMap<Long, String> map = new HashMap<>();
                for (Exam e : listCourse) {
                    map.put(e.getId(), e.getLogicClassIds());
                    // 遍历map获取 value 并使用逗号分割 logicClassIds，
                    // 将分割后的数组与前台传过来并同样分割好的数组进行比对得到匹配的数据的id列表
                    String[] requestIds = logicClassIds.split(",");
                    String[] classIds;
                    // int count = 0;
                    for (Map.Entry<Long, String> entry : map.entrySet()) {
                        classIds = entry.getValue().split(",");
                        for (String logicClassId : classIds) {
                            //其中一个班级存在即可
                            int flag = 0;
                            for (String requestId : requestIds) {
                                if (requestId.equals(logicClassId) && e.getIsPublish()) {
                                    //将已发布的符合要求的exam添加到集合中去
                                    ids.add(e.getCourseId());
                                    System.out.println("ids大小:" + ids.size() + "值为" + entry.getKey());
                                    //标识此条数据正确
                                    //将结果添加到集合中去
                                    examList.add(e);
                                    System.out.println(examList.size());
                                    flag = 1;
                                    break;
                                }
                            }
                            // 判定此条数据正确,直接结束当前数据班级信息的循环
                            if (flag == 1) {

                                break;
                            }

                            /** 所有班级都存在才可
                             *
                             *   if (requestIds[count].equals(logicClassId)) {
                             *
                             *   }
                             *   if (count == requestIds.length) {
                             *       ids.add(entry.getKey());
                             *      count = 0;
                             *       break;
                             *   }
                             */
                        }
                    }
                    // 获取根据条件查询到的page
                    page = examRepository.findAllByIdIsIn(ids, pageable);
                    // 获取列表

                    // 遍历
                    for (Exam exam : examList) {
                        // 对每个数据进行完整性填充
                        completeEntity(exam);
                    }
                }
            }
            return page;
        }
    }

}
