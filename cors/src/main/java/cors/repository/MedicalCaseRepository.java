package cors.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cors.domain.ClinicalDiagnosis;
import cors.domain.MedicalCase;

public interface MedicalCaseRepository extends  JpaRepository<MedicalCase,Long>{

	public List<MedicalCase> findByNumber(String number);
	
	public List<MedicalCase> findByNumberOrNameOrClinical(String number,String name,ClinicalDiagnosis clinical);
	
}
