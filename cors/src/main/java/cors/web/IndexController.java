package cors.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@Autowired
	private ApplicationContext context;
	
	 @RequestMapping(value="/",method=RequestMethod.GET)
	    public String index() {
//		 context.getBean("mapperScannerConfigurer");
	        return "redirect:/medicalCase/query";
		}
 
}
