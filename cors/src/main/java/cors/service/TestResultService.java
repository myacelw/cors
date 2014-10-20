package cors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cors.domain.TestResult;
import cors.repository.TestResultRepository;

@Service
public class TestResultService {

	@Autowired
	private TestResultRepository repository;

    public TestResult get(Long id) {  
		return repository.findOne(id);
	}
	
	
}
