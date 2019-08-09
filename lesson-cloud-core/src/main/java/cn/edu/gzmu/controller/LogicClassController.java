package cn.edu.gzmu.controller;

import cn.edu.gzmu.model.constant.LessonResource;
import cn.edu.gzmu.model.entity.LogicClass;
import cn.edu.gzmu.service.LogicClassService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * LogicClass Controller
 *
 * @author Japoul
 * @version 1.0
 * @date 2019-5-28 17:24:38
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping(LessonResource.LOGIC_CLASS_SEARCH)
public class LogicClassController extends BaseController<LogicClass, LogicClassService, Long> {
    private final @NonNull LogicClassService logicClassService;

    @GetMapping("/student")
    public  HttpEntity<?> student(LogicClass logicClass){
        return ResponseEntity.ok(logicClassService.getAllCourseTimetableLocation(logicClass));
    }

}
