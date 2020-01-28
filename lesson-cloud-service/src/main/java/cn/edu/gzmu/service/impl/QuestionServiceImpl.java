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
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

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

}
