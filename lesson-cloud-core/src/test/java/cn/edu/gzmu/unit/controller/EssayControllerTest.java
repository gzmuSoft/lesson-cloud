package cn.edu.gzmu.unit.controller;

import cn.edu.gzmu.model.entity.Essay;
import cn.edu.gzmu.repository.entity.CourseRepository;
import cn.edu.gzmu.repository.entity.EssayRepository;
import cn.edu.gzmu.repository.entity.KnowledgeRepository;
import cn.edu.gzmu.repository.entity.SectionRepository;
import cn.edu.gzmu.service.EssayService;
import cn.edu.gzmu.service.impl.EssayServiceImpl;
import org.junit.jupiter.api.BeforeEach;
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
 * @date 2020/1/10 下午8:34
 */
@DataJpaTest
public class EssayControllerTest {

    private EssayService essayService;

    @Autowired
    private EssayRepository essayRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private KnowledgeRepository knowledgeRepository;

    private PageRequest page = PageRequest.of(0, 10);

    @BeforeEach
    void setUp() {
        essayService = new EssayServiceImpl(essayRepository, courseRepository, sectionRepository, knowledgeRepository);
    }

    @Test
    void findAllByCourseIdWhenPassed() {
        Page<Essay> essays = essayService.findAllByCourseId(1L, page);
        assertEquals(2, essays.getTotalElements());
    }
}
