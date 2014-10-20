package cors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cors.domain.MedicalCase;
import cors.repository.MedicalCaseRepository;
import cors.repository.TestResultRepository;

@Service
public class MedicalCaseService {

	@Autowired
	private MedicalCaseRepository repository;

	@Autowired
	private TestResultRepository  testResultrepository;
	
	
    public MedicalCase get(Long id) {  
    	MedicalCase c= repository.findOne(id);

 
    	
    	return c;
    	
	}
	
    
	
}
