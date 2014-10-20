package cors.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cors.domain.TestResult;
import cors.domain.TestType;

public interface TestResultRepository extends  JpaRepository<TestResult,Long>{

	public List<TestResult> findByTypeAndMedicalCaseId(TestType type, Long id);
	
	
}
