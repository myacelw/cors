package cors.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cors.domain.Symptom;

public interface SymptomRepository extends  JpaRepository<Symptom,Long>{
}
