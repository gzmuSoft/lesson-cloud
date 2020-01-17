package cn.edu.gzmu.service;

import cn.edu.gzmu.repository.entity.*;
import cn.edu.gzmu.service.impl.TeacherServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @date 2020/1/14 下午11:59
 */
@DataJpaTest
public class TeacherServiceTest {

    private TeacherService teacherService;
    @Autowired
    private EssayRepository essayRepository;
    @Autowired
    private JudgementRepository judgementRepository;
    @Autowired
    private MultiSelRepository multiSelRepository;
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private SingleSelRepository singleSelRepository;

    @BeforeEach
    void setUp() {
        teacherService = new TeacherServiceImpl(essayRepository, judgementRepository,
                multiSelRepository, programRepository, singleSelRepository);
    }

    @Test
    @Tag("passed")
    @DisplayName("查询所有公开题目")
    void findPublicQuestionBankByConditionWhenPassedIsPublicTrue() {
        JSONObject result = teacherService.findPublicQuestionBankByCondition(
                0L, 0L, 0L, "", true, PageRequest.of(0, 10)
        );
        assertMethod(result, 3L, 2L, 2L, 4L, 3L);
    }

    @Test
    @Tag("passed")
    @DisplayName("根据 name 查询公开题目")
    void findPublicQuestionBankByConditionWhenPassedIsPublicTrueAndName() {
        JSONObject result = teacherService.findPublicQuestionBankByCondition(
                0L, 0L, 0L, "你是", true, PageRequest.of(0, 10)
        );
        assertMethod(result, 2L, 1L, 0L, 0L, 0L);
    }

    @Test
    @Tag("passed")
    @DisplayName("根据 courseId 查询公开题目")
    void findPublicQuestionBankByConditionWhenPassedIsPublicTrueAndCourseId() {
        JSONObject result = teacherService.findPublicQuestionBankByCondition(
                2L, 0L, 0L, "", true, PageRequest.of(0, 10)
        );
        assertMethod(result, 1L, 1L, 1L, 1L, 1L);
    }

    @Test
    @Tag("passed")
    @DisplayName("根据 courseId 模糊查询公开题目")
    void findPublicQuestionBankByConditionWhenPassedIsPublicTrueAndCourseIdAndName() {
        JSONObject result = teacherService.findPublicQuestionBankByCondition(
                2L, 0L, 0L, "2+", true, PageRequest.of(0, 10)
        );
        assertMethod(result, 0L, 0L, 0L, 0L, 1L);
    }

    @Test
    @Tag("passed")
    @DisplayName("根据 courseId 和 sectionId 查询公开题目")
    void findPublicQuestionBankByConditionWhenPassedIsPublicTrueAndCourseIdAndSectionId() {
        JSONObject result = teacherService.findPublicQuestionBankByCondition(
                2L, 2L, 0L, "", true, PageRequest.of(0, 10)
        );
        assertMethod(result, 1L, 0L, 1L, 0L, 0L);
    }

    @Test
    @Tag("passed")
    @DisplayName("根据 courseId 和 sectionId 模糊查询公开题目")
    void findPublicQuestionBankByConditionWhenPassedIsPublicTrueAndCourseIdAndSectionIdAndName() {
        JSONObject result = teacherService.findPublicQuestionBankByCondition(
                2L, 2L, 0L, "公", true, PageRequest.of(0, 10)
        );
        assertMethod(result, 1L, 0L, 0L, 0L, 0L);
    }

    @Test
    @Tag("passed")
    @DisplayName("根据 courseId 和 sectionId 和 knowledgeId 查询公开题目")
    void findPublicQuestionBankByConditionWhenPassedIsPublicTrueAndCourseIdAndSectionIdAndKnowledgeId() {
        JSONObject result = teacherService.findPublicQuestionBankByCondition(
                6L, 5L, 2L, "", true, PageRequest.of(0, 10)
        );
        assertMethod(result, 0L, 0L, 0L, 2L, 0L);
    }

    @Test
    @Tag("passed")
    @DisplayName("根据 courseId 和 sectionId 和 knowledgeId 模糊查询公开题目")
    void findPublicQuestionBankByConditionWhenPassedIsPublicTrueAndCourseIdAndSectionIdAndKnowledgeIdAndName() {
        JSONObject result = teacherService.findPublicQuestionBankByCondition(
                6L, 5L, 2L, "李", true, PageRequest.of(0, 10)
        );
        assertMethod(result, 0L, 0L, 0L, 1L, 0L);
    }

    /**
     * 断言函数
     *
     * @param result            json对象
     * @param essayExpected     essay期望值
     * @param judgementExpected judgement期望值
     * @param multiSelExpected  multiSel期望值
     * @param programExpected   program期望值
     * @param singleSelExpected singleSel期望值
     * @author Soul
     * @date 2020/1/15 13:36
     */
    private void assertMethod(JSONObject result, Long essayExpected, Long judgementExpected, Long multiSelExpected, Long programExpected, Long singleSelExpected) {
        assertEquals(essayExpected, result.getObject("essay", Page.class).getTotalElements());
        assertEquals(judgementExpected, result.getObject("judgement", Page.class).getTotalElements());
        assertEquals(multiSelExpected, result.getObject("multiSel", Page.class).getTotalElements());
        assertEquals(programExpected, result.getObject("program", Page.class).getTotalElements());
        assertEquals(singleSelExpected, result.getObject("singleSel", Page.class).getTotalElements());
    }
}
