package cors.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cors.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

    @RequestMapping("/{id}")  
    public String view(@PathVariable("id") Long id, Model model) {
    	
    	model.addAttribute(service.get(id));
		return "view";
	}

}
