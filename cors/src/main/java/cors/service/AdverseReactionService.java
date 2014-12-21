package cors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cors.repository.AdverseReactionRepository;

@Service
public class AdverseReactionService {

	@Autowired
	private AdverseReactionRepository repository;

	
}
