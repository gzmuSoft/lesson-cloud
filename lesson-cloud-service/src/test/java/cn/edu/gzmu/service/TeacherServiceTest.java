package cn.edu.gzmu.service;

import cn.edu.gzmu.repository.entity.*;
import cn.edu.gzmu.service.TeacherService;
import cn.edu.gzmu.service.impl.TeacherServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
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
    @DisplayName("查询公开题目")
    void findPublicQuestionBankByConditionWhenPassedIsPublicTrue() {
        JSONObject result = teacherService.findPublicQuestionBankByCondition(
                0L, 0L, 0L, "", true, PageRequest.of(0, 10)
        );
        assertEquals(2, result.getObject("essay", Page.class).getTotalElements());
    }
}
