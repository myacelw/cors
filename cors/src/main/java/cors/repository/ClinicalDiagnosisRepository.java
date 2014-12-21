package cors.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cors.domain.ClinicalDiagnosis;

public interface ClinicalDiagnosisRepository extends  JpaRepository<ClinicalDiagnosis,Long>{
}
