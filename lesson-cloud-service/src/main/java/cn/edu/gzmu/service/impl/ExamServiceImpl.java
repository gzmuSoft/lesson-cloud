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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
 * @author YMS
 * @date 2019-8-13 23:12:41
 * <p>
 *
 * <p>
 *  @author hzl
 *  @date 2019-8-13 23:48:10
 *  获取到当前教师未发布的考试信息
 *
 *  </p>
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
     *
     * 获取到当前教师未发布的考试信息
     *
     * 先获取到当前教师的所有逻辑班级ids
     * 如果传入了逻辑班级ids，取跟上一步的交集
     * 搜索出所有的未发布的exam信息，如果有课程id则增加这个条件
     * 通过上面的条件由逻辑班级ids查询
     */
    @Override
    public Page<Exam> getAllUnPublishExam(Teacher teacher,String logicClassIds,String courseId,Pageable pageable){
        //先获取到当前教师的所有逻辑班级
       List<LogicClass> logicClasses= new ArrayList<>(logicClassRepository.findDistinctByTeacherId(teacher.getId())) ;
        List<Long> ids1=new ArrayList<>();
        for (LogicClass aClass : logicClasses) {
            ids1.add(aClass.getId());
        }
        //如果传入logicClassIds
        if (logicClassIds!=null){
            String[] requestIds = logicClassIds.split(",");
            List<Long> ids2=new ArrayList<>();
            for (String requestId : requestIds) {
                ids2.add(Long.valueOf(requestId));
            }
            //求交集
            ids1.retainAll(ids2);
        }
        List<Exam> examList;
        //查询所有的未发布的考试信息
        //如果没传入courseId
        if (courseId==null){
            examList=examRepository.findDistinctByIsPublishFalse();
        }else {
            examList=examRepository.findDistinctByCourseIdAndIsPublishFalse(Long.parseLong(courseId));
        }

        //以下为copy上面(searchByClassAndCourse)的
        String[] classIds;
        HashMap<Long, String> map = new HashMap<>();
        List<Long> ids = new ArrayList<Long>();
        for (Exam e : examList) {
            map.put(e.getId(), e.getLogicClassIds());
        }
        for (Map.Entry<Long, String> entry : map.entrySet()) {
            classIds = entry.getValue().split(",");
            for (String logicClassId : classIds) {
                //其中一个班级存在即可
                int flag = 0;
                for (Long id : ids1) {
                    //for (Long requestId : ids1) {
                    if (id == Long.parseLong(logicClassId)) {
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

    /**
     * 查询所有考试的详细统计信息
     * 1.题目数量（通过组卷规则）
     * 2.所有参与考试的逻辑班级名称
     * 3.应参加考试的人数（通过当前考试关联的逻辑班级的所有班级人数以及重修人数相加）
     * 4.考试所有信息
     * 5.条件可能为逻辑班级id和学期id（可能没有）
     *
     * 未完成
     * @author YMS
     */
    @Override
    public Page<ExamDetailsDto> searchDetailsAll(Pageable pageable) {
        //1.获取所有的考试
        Page<Exam> exams = examRepository.findAll(pageable);
        ArrayList<ExamDetailsDto> examDetailsDtos = new ArrayList<>();
//        Page<ExamDetailsDto> pages = ;
        for (Exam exam : exams) {
            //2.根据考试id获取考试规则
            List<ExamRule> examRules = new ArrayList<>();
            //3.考试题目数量
            int count = examRules.stream().mapToInt(ExamRule::getQuestionCount).sum();
            //4.所有参加的逻辑班级的名称
            ArrayList<LogicClass> logicClasses = new ArrayList<>();
            for (String s : exam.getLogicClassIds().split(",")) {
                Page<LogicClass> allById = logicClassRepository.findAllById(Long.valueOf(s), pageable);
                logicClasses.addAll(allById.getContent());
            }
            String classNames = "";
            int peopleNum = 0;
            for (LogicClass logicClass : logicClasses) {
                classNames += logicClass.getName();
                //5.应参加人数
//                peopleNum += logicClass.
            }

            examDetailsDtos.add(ExamDetailsDto.builder().exam(exam).count(count)
                    .logicClasses(classNames).build());
        }

        //不会写，等理解了再重写，先交了再说 QAQ
        return null;
    }
}
