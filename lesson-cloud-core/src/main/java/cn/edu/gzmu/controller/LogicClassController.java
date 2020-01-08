package cn.edu.gzmu.controller;

import cn.edu.gzmu.auth.helper.OauthHelper;
import cn.edu.gzmu.model.constant.LessonResource;
import cn.edu.gzmu.model.entity.LogicClass;
import cn.edu.gzmu.service.LogicClassService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * LogicClass Controller
 *
 * @author Japoul
 * @version 1.0
 * @date 2019-5-28 17:24:38
 *
 *<p>
 * 获取当前登录学生的所有逻辑班级（课程）上课时间表及地点信息，不分页
 * @author hzl
 * @date 2019-8-13 15:31</p>
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping(LessonResource.LOGIC_CLASS_SEARCH)
public class LogicClassController extends BaseController<LogicClass, LogicClassService, Long> {
    private final @NonNull LogicClassService logicClassService;
    private final @NonNull OauthHelper oauthHelper;

    @GetMapping(LessonResource.STUDENT)
    @Secured("ROLE_STUDENT")
    public  HttpEntity<?> student(){
        return ResponseEntity.ok(logicClassService.findAllCourseTimetableLocation(oauthHelper.student()));
    }

}
