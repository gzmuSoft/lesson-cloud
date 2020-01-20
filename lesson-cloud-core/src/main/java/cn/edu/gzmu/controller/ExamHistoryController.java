package cn.edu.gzmu.controller;

import cn.edu.gzmu.service.helper.OauthHelper;
import cn.edu.gzmu.model.constant.LessonResource;
import cn.edu.gzmu.model.entity.ExamHistory;
import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.service.ExamHistoryService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ExamHistory Controller
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-28 17:24:38
 */
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping(LessonResource.EXAM_HISTORY_SEARCH)
public class ExamHistoryController extends BaseController<ExamHistory, ExamHistoryService, Long> {

    private final @NonNull ExamHistoryService examHistoryService;
    private final @NonNull OauthHelper oauthHelper;

    /**
     * 获取当前登录学生的考试历史信息
     * 只有拥有学生角色以后才能够访问当前接口
     *
     * @param pageable 分页
     * @return 响应
     */
    @GetMapping("/student")
    public HttpEntity<?> fromStudent(@PageableDefault(sort = {"sort", "id"}) Pageable pageable) {
        // 获取当前登录用户
        Student student = oauthHelper.student();
        // 请求响应构建
        return ResponseEntity.ok(
                examHistoryService.searchByStudentPage(student, pageable)
        );
    }

}
