package cn.edu.gzmu.repository.entity;

import cn.edu.gzmu.model.entity.Paper;
import cn.edu.gzmu.repository.base.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


/**
 * Paper Repository
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-23 17:38:13
 */
@RepositoryRestResource(path = "/paper")
public interface PaperRepository extends BaseRepository<Paper, Long> {

    /**
     * 根据考试 id 获取所有试卷信息
     *
     * @param examId
     * @return 所有id为examid的试卷
     */
    List<Paper> findAllByExamId(Long examId);

}