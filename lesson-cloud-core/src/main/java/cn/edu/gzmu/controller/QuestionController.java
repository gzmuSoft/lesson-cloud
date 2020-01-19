package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.constant.LessonResource;
import cn.edu.gzmu.model.entity.Question;
import cn.edu.gzmu.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/19 下午4:30
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping(LessonResource.QUESTION_SEARCH)
public class QuestionController extends BaseController<Question, QuestionService, Long>{
}
