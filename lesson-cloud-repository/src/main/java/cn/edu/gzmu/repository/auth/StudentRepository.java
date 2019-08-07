package cn.edu.gzmu.repository.auth;


import cn.edu.gzmu.model.annoection.AuthorizationRepository;

import static cn.edu.gzmu.model.constant.LessonResource.STUDENT;

/**
 * Student Repository
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @version 1.0
 * @date 2019-5-23 17:38:13
 */
@AuthorizationRepository
public class StudentRepository {
    public static final String GET_ONE = STUDENT + "/one/";
    public static final String GET_MORE = STUDENT;
    public static final String GET_SELF = STUDENT + "/self";

    /**
     * 获取一个资源的路径
     *
     * @param id id
     * @return 结果
     */
    public String getOnePath(Long id) {
        return GET_ONE + id;
    }

    /**
     * 获取多个资源的路径
     *
     * @return 结果
     */
    public String getMorePath() {
        return GET_MORE;
    }

    /**
     * 获取自己资源的路径
     *
     * @return 结果
     */
    public String getSelf() {
        return GET_SELF;
    }

}