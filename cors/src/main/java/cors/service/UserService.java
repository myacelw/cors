package cors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cors.domain.User;
import cors.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

    public User get(Long id) {  
		return repository.findOne(id);
	}
	
	
}
