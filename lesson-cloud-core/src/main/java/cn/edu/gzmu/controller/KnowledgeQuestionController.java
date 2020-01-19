package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.constant.LessonResource;
import cn.edu.gzmu.model.entity.KnowledgeQuestion;
import cn.edu.gzmu.service.KnowledgeQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/19 下午4:31
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping(LessonResource.KNOWLEDGE_QUESTION_SEARCH)
public class KnowledgeQuestionController extends BaseController<KnowledgeQuestion, KnowledgeQuestionService, Long>{
}
