package cn.edu.gzmu.repository.dto;

import cn.edu.gzmu.model.dto.QuestionView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "questionView")
public interface QuestionViewRepository extends JpaRepository<QuestionView, Long>, JpaSpecificationExecutor<QuestionView> {
}
