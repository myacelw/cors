package cors.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cors.domain.ConditionGroup;

public interface ConditionGroupRepository extends  JpaRepository<ConditionGroup,Long>{

}
