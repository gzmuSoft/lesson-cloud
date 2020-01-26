package cn.edu.gzmu.service;

import cn.edu.gzmu.model.entity.Teacher;
import cn.edu.gzmu.repository.entity.KnowledgeQuestionRepository;
import cn.edu.gzmu.repository.entity.KnowledgeRepository;
import cn.edu.gzmu.repository.entity.QuestionRepository;
import cn.edu.gzmu.repository.entity.SectionRepository;
import cn.edu.gzmu.service.helper.OauthHelper;
import cn.edu.gzmu.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @date 2020/1/14 下午11:59
 */
@DataJpaTest
public class TeacherServiceTest {
    private Teacher teacher = new Teacher();
    private TeacherService teacherService;
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private KnowledgeRepository knowledgeRepository;

    @Autowired
    private KnowledgeQuestionRepository knowledgeQuestionRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @MockBean
    private OauthHelper oauthHelper;

    @BeforeEach
    void setUp() {
        teacher.setName("admin");
        teacherService = new TeacherServiceImpl(
                questionRepository, knowledgeQuestionRepository,
                knowledgeRepository, sectionRepository, oauthHelper);
    }

    @Test
    @Tag("passed")
    @DisplayName("查询所有公开题目")
    void findQuestionBankByConditionWhenPassedIsPublicTrue() {
        assertEquals(14L, teacherService.findQuestionBankCondition(
                0L, 0L, 0L, 0L, "", null, true, PageRequest.of(0, 10))
                .getTotalElements()
        );
    }

    @Test
    @Tag("passed")
    @DisplayName("根据 name 查询公开题目")
    void findQuestionBankByConditionWhenPassedIsPublicTrueAndName() {
        assertEquals(3L, teacherService.findQuestionBankCondition(
                0L, 0L, 0L, 0L, "你是", null, true, PageRequest.of(0, 10))
                .getTotalElements()
        );
    }

    @Test
    @Tag("passed")
    @DisplayName("根据 courseId 查询公开题目")
    void findQuestionBankByConditionWhenPassedIsPublicTrueAndCourseId() {
        assertEquals(5L, teacherService.findQuestionBankCondition(
                0L, 2L, 0L, 0L, "", null, true, PageRequest.of(0, 10))
                .getTotalElements()
        );
    }

    @Test
    @Tag("passed")
    @DisplayName("根据 courseId 模糊查询公开题目")
    void findPublicQuestionBankByConditionWhenPassedIsPublicTrueAndCourseIdAndName() {
        assertEquals(1L, teacherService.findQuestionBankCondition(
                0L, 2L, 0L, 0L, "2+", null, true, PageRequest.of(0, 10))
                .getTotalElements()
        );
    }

    @Test
    @Tag("passed")
    @DisplayName("根据 courseId 和 sectionId 查询公开题目")
    void findQuestionBankByConditionWhenPassedIsPublicTrueAndCourseIdAndSectionId() {
        assertEquals(2L, teacherService.findQuestionBankCondition(
                0L, 2L, 2L, 0L, "", null, true, PageRequest.of(0, 10))
                .getTotalElements()
        );
    }

    @Test
    @Tag("passed")
    @DisplayName("根据 courseId 和 sectionId 模糊查询公开题目")
    void findQuestionBankByConditionWhenPassedIsPublicTrueAndCourseIdAndSectionIdAndName() {
        assertEquals(1L, teacherService.findQuestionBankCondition(
                0L, 2L, 2L, 0L, "公", null, true, PageRequest.of(0, 10))
                .getTotalElements()
        );
    }

    @Test
    @Tag("passed")
    @DisplayName("根据 courseId 和 sectionId 和 knowledgeId 查询公开题目")
    void findQuestionBankByConditionWhenPassedIsPublicTrueAndCourseIdAndSectionIdAndKnowledgeId() {
        assertEquals(2L, teacherService.findQuestionBankCondition(
                0L, 6L, 5L, 2L, "", null, true, PageRequest.of(0, 10))
                .getTotalElements()
        );
    }

    @Test
    @Tag("passed")
    @DisplayName("根据 courseId 和 sectionId 和 knowledgeId 模糊查询公开题目")
    void findQuestionBankByConditionWhenPassedIsPublicTrueAndCourseIdAndSectionIdAndKnowledgeIdAndName() {
        assertEquals(1L, teacherService.findQuestionBankCondition(
                0L, 6L, 5L, 2L, "李", null, true, PageRequest.of(0, 10))
                .getTotalElements()
        );
    }

    @Test
    @Tag("passed")
    @DisplayName("查询登录教师的私有题目")
    void findQuestionBankByConditionWhenPassedIsPublicFalse() {
        assertEquals(14L, teacherService.findQuestionBankCondition(
                0L, 0L, 0L, 0L, "", null, false, PageRequest.of(0, 10))
                .getTotalElements()
        );
    }

    @Test
    @Tag("passed")
    @DisplayName("根据 name 查询登录教师的私有题目")
    void findQuestionBankByConditionWhenPassedIsPublicFalseAndName() {
        assertEquals(3L, teacherService.findQuestionBankCondition(
                0L, 0L, 0L, 0L, "你是", null, false, PageRequest.of(0, 10))
                .getTotalElements()
        );
    }

    @Test
    @Tag("passed")
    @DisplayName("根据 courseId 查询登录教师的私有题目")
    void findQuestionBankByConditionWhenPassedIsPublicFalseAndCourseId() {
        assertEquals(5L, teacherService.findQuestionBankCondition(
                0L, 2L, 0L, 0L, "", null, false, PageRequest.of(0, 10))
                .getTotalElements()
        );
    }

    @Test
    @Tag("passed")
    @DisplayName("根据 courseId 模糊查询登录教师的私有题目")
    void findPublicQuestionBankByConditionWhenPassedIsPublicFalseAndCourseIdAndName() {
        assertEquals(1L, teacherService.findQuestionBankCondition(
                0L, 2L, 0L, 0L, "2+", null, false, PageRequest.of(0, 10))
                .getTotalElements()
        );
    }

    @Test
    @Tag("passed")
    @DisplayName("根据 courseId 和 sectionId 查询登录教师的私有题目")
    void findQuestionBankByConditionWhenPassedIsPublicFalseAndCourseIdAndSectionId() {
        assertEquals(2L, teacherService.findQuestionBankCondition(
                0L, 2L, 2L, 0L, "", null, false, PageRequest.of(0, 10))
                .getTotalElements()
        );
    }

    @Test
    @Tag("passed")
    @DisplayName("根据 courseId 和 sectionId 模糊查询登录教师的私有题目")
    void findQuestionBankByConditionWhenPassedIsPublicFalseAndCourseIdAndSectionIdAndName() {
        assertEquals(1L, teacherService.findQuestionBankCondition(
                0L, 2L, 2L, 0L, "公", null, false, PageRequest.of(0, 10))
                .getTotalElements()
        );
    }

    @Test
    @Tag("passed")
    @DisplayName("根据 courseId 和 sectionId 和 knowledgeId 查询登录教师的私有题目")
    void findQuestionBankByConditionWhenPassedIsPublicFalseAndCourseIdAndSectionIdAndKnowledgeId() {
        assertEquals(2L, teacherService.findQuestionBankCondition(
                0L, 6L, 5L, 2L, "", null, false, PageRequest.of(0, 10))
                .getTotalElements()
        );
    }

    @Test
    @Tag("passed")
    @DisplayName("根据 courseId 和 sectionId 和 knowledgeId 模糊查询登录教师的私有题目")
    void findQuestionBankByConditionWhenPassedIsPublicFalseAndCourseIdAndSectionIdAndKnowledgeIdAndName() {
        assertEquals(1L, teacherService.findQuestionBankCondition(
                0L, 6L, 5L, 2L, "李", null, false, PageRequest.of(0, 10))
                .getTotalElements()
        );
    }
}
