package cors.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cors.domain.AdverseReaction;

public interface AdverseReactionRepository extends  JpaRepository<AdverseReaction,Long>{

	List<AdverseReaction> findByMedicalCaseId(Long id);
}
