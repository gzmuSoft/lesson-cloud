package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Question;
import cn.edu.gzmu.repository.entity.QuestionRepository;
import cn.edu.gzmu.service.QuestionService;
import org.springframework.stereotype.Service;

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/19 下午4:28
 */
@Service
public class QuestionServiceImpl extends BaseServiceImpl<QuestionRepository, Question, Long>
        implements QuestionService {
}
