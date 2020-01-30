package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Knowledge;
import cn.edu.gzmu.model.entity.KnowledgeQuestion;
import cn.edu.gzmu.model.entity.Question;
import cn.edu.gzmu.model.entity.Section;
import cn.edu.gzmu.model.exception.ResourceNotFoundException;
import cn.edu.gzmu.repository.entity.KnowledgeQuestionRepository;
import cn.edu.gzmu.repository.entity.KnowledgeRepository;
import cn.edu.gzmu.repository.entity.QuestionRepository;
import cn.edu.gzmu.repository.entity.SectionRepository;
import cn.edu.gzmu.service.QuestionService;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/19 下午4:28
 */
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl extends BaseServiceImpl<QuestionRepository, Question, Long>
        implements QuestionService {

    private final @NonNull KnowledgeQuestionRepository knowledgeQuestionRepository;
    private final @NonNull QuestionRepository questionRepository;
    private final @NonNull KnowledgeRepository knowledgeRepository;
    private final @NonNull SectionRepository sectionRepository;

    /**
     * 根据知识点 ids 和 ids 的数量查询含有这些知识点的题目的 id.
     *
     * @param ids 知识点 id 列表
     * @return 包含这些知识点的题目.
     */
    @Override
    public List<Question> getQuestionIdByKnowledgeIds(List<Long> ids) {
        return getQuestionDecision(null, null, ids);
    }

    /**
     * 根据章 ids 查询属于这些章的题目.
     *
     * @param ids 章 id 列表
     * @return 属于这些节的题目
     */
    @Override
    public List<Question> getQuestionBySectionIds(List<Long> ids) {
        return getQuestionDecision(ids, null, null);
    }

    /**
     * 根据节 ids 查询属于这些节的题目.
     *
     * @param ids 节 id 列表
     * @return 属于这些节的题目
     */
    @Override
    public List<Question> getQuestionByPassageIds(List<Long> ids) {
        return getQuestionDecision(null, ids, null);
    }

    /**
     * 决策获取题目
     *
     * @param sectionIds   章 ids 没有即为 null
     * @param passageIds   节 ids 没有即为 null
     * @param knowledgeIds 知识点 ids 没有即为 null
     * @return 对应的题目.
     */
    private List<Question> getQuestionDecision(List<Long> sectionIds, List<Long> passageIds, List<Long> knowledgeIds) {

        List<Long> questionIds = new ArrayList<>();
        List<Long> sectionIds1 = new ArrayList<>();
        Set<Long> knowledgeIds1;

        // 根据章时需要将下列节加入ids查询知识点
        if (Objects.nonNull(sectionIds)) {
            sectionIds1 = sectionRepository.findAllByParentIdIn(sectionIds)
                    .stream().map(Section::getId).collect(Collectors.toList());
            // 章也可能绑定知识点
            sectionIds1.addAll(sectionIds);

        }
        // 直接根据节 ids
        if (Objects.nonNull(passageIds)) {
            sectionIds1 = passageIds;
        }
        // 根据构造好的 sectionIds 查询知识点后查询所属问题
        if (Objects.nonNull(sectionIds) || Objects.nonNull(passageIds)) {
            knowledgeIds1 = knowledgeRepository.findAllBySectionIdIn(sectionIds1)
                    .stream().map(Knowledge::getId).collect(Collectors.toSet());
            questionIds = knowledgeQuestionRepository.findAllByKnowledgeIdIn(new ArrayList<>(knowledgeIds1))
                    .stream().map(KnowledgeQuestion::getQuestionId).collect(Collectors.toList());
        }
        // 根据知识点 ids 和 ids 的数量查询含有这些知识点的题目的 id
        if (Objects.nonNull(knowledgeIds)) {
            questionIds = knowledgeQuestionRepository.getQuestionIdByKnowledgeId(knowledgeIds, knowledgeIds.size());
        }
        return questionRepository.findAllByIdIn(questionIds);
    }

    /**
     * 根据题目 id 查询关联的章、节、知识点 ids
     *
     * @param id 题目id
     * @return 结果.
     */
    @Override
    public JSONObject getQuestionCorrelationById(Long id) {
        JSONObject result = new JSONObject();
        // 获得知识点 ids
        List<Long> knowledgeIds = knowledgeQuestionRepository.findAllByQuestionId(id)
                .stream().map(KnowledgeQuestion::getKnowledgeId).collect(Collectors.toList());
        // 获得章和节的 ids 集合
        Set<Long> spSet = knowledgeRepository.findDistinctByIdIn(knowledgeIds)
                .stream().map(Knowledge::getSectionId).collect(Collectors.toSet());
        Set<Section> sections = sectionRepository.findDistinctByIdIn(new ArrayList<>(spSet));
        // 分离节 ids
        Set<Long> passageIdSet = sections.stream()
                .filter(section -> section.getParentId() != 0)
                .map(Section::getId).collect(Collectors.toSet());
        // 得到节的关联章 ids
        Set<Long> sectionIdSet = sections.stream()
                .filter(section -> section.getParentId() != 0)
                .map(Section::getParentId).collect(Collectors.toSet());
        // 补充章 ids
        spSet.removeAll(passageIdSet);
        sectionIdSet.addAll(spSet);
        // 获得课程 id （一个题目只有一个课程情况）
        Long courseId = 0L;
        if (sectionIdSet.size() > 0) {
            // 一个题目只有一个课程的情况
            courseId = sectionRepository.findById(new ArrayList<>(sectionIdSet).get(1))
                    .orElseThrow(() -> new ResourceNotFoundException("该题目的课程查询失败")).getCourseId();
        }
        // 压入 JSON 对象
        result.put("knowledgeIds", knowledgeIds);
        result.put("sectionIds", sectionIdSet);
        result.put("passageIds", passageIdSet);
        result.put("courseId", courseId);
        return result;
    }

    /**
     * 根据题目 id 查询关联的知识点
     *
     * @param id 题目id
     * @return 结果.
     */
    @Override
    public Set<Knowledge> getKnowledgeSetById(Long id) {
        return knowledgeRepository.findDistinctByIdIn(
                knowledgeQuestionRepository.findAllByQuestionId(id)
                        .stream().map(KnowledgeQuestion::getKnowledgeId)
                        .collect(Collectors.toList())
        );
    }

    /**
     * 保存/修改题目入口.
     *
     * @param ids      知识点 ids
     * @param question 题目对象
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Question saveOrUpdateQuestion(List<Long> ids, Question question) {
        //判断如果是更新则调用更新方法
        if (Objects.nonNull(question.getId())) {
            // 删除废弃的知识点关联并返回需要增加的知识点 ids
            ids = updateQuestion(ids, question);
        }
        // 保存题目
        questionRepository.save(question);
        // 更新知识点与题目的关联
        List<KnowledgeQuestion> knowledgeQuestions = new LinkedList<>();
        if (ids.size() != 0) {
            ids.forEach(id -> {
                KnowledgeQuestion knowledgeQuestion = new KnowledgeQuestion();
                knowledgeQuestion.setKnowledgeId(id).setQuestionId(question.getId());
                knowledgeQuestions.add(knowledgeQuestion);
            });
            knowledgeQuestionRepository.saveAll(knowledgeQuestions);
        }
        return question;
    }

    /**
     * 删除题目并解除关联.
     *
     * @param id 题目 id
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("此题目不存在"));
        question.setIsEnable(false);
        questionRepository.save(question);
        List<KnowledgeQuestion> deleteCorrelation =
                knowledgeQuestionRepository.findAllByQuestionId(id);
        deleteCorrelation.forEach(model -> model.setIsEnable(false));
        knowledgeQuestionRepository.saveAll(deleteCorrelation);
    }

    /**
     * 更新题目解除关联.
     *
     * @param ids      知识点 ids
     * @param question 题目对象
     */
    private List<Long> updateQuestion(List<Long> ids, Question question) {
        // 修改题目
        questionRepository.save(question);
        // 获取数据库待采购物料数据进行对比
        List<KnowledgeQuestion> knowledgeQuestionsDb = knowledgeQuestionRepository
                .findAllByQuestionId(question.getId());
        // 用于根据待删除的知识点 ids 获取待删除的关联表 ids
        Map<Long, Long> collect = knowledgeQuestionsDb.stream()
                .collect(Collectors.toMap(KnowledgeQuestion::getKnowledgeId, KnowledgeQuestion::getId));
        // set1 存放为数据库数据.
        HashSet<Long> set1 = (HashSet<Long>) knowledgeQuestionsDb
                .stream().map(KnowledgeQuestion::getKnowledgeId)
                .collect(Collectors.toSet());
        // set2 存放更新后的数据.
        HashSet<Long> set2 = new HashSet<>(ids);
        // 做差集得到新增和被删除的知识点 ids
        Sets.SetView<Long> deletedKnowledgeIds = Sets.difference(set1, set2);
        // 待删除的 ids 与前端的 ids 的并集
        Sets.SetView<Long> unionIds = Sets.union(deletedKnowledgeIds, set2);
        // 得到的并集与数据库中的 ids 做差集得到出需要新增的知识点 ids
        Sets.SetView<Long> addKnowledgeIds = Sets.difference(unionIds, set1);
        // 获取待删除的关联表的 ids
        Set<Long> deletedIds = new HashSet<>();
        deletedKnowledgeIds.forEach(id -> deletedIds.add(collect.get((Long) id)));
        // 执行删除
        List<KnowledgeQuestion> deleteCorrelation =
                knowledgeQuestionRepository.findAllByIdIn(new ArrayList<>(deletedIds));
        deleteCorrelation.forEach(model -> model.setIsEnable(false));
        knowledgeQuestionRepository.saveAll(deleteCorrelation);
        // 返回新增的知识点 ids
        return new ArrayList<>(addKnowledgeIds);
    }


}
