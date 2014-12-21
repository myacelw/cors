package cors.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cors.domain.Symptom;
import cors.repository.SymptomRepository;


@Controller
@RequestMapping("/symptom")
public class SymptomController {


	@Autowired
	private SymptomRepository repository;
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(Long id,Model model) {
		Symptom symptom=null;
		if(id!=null){
			symptom=repository.findOne(id);
		}else{
			symptom=new Symptom();
		}
		model.addAttribute("symptom",symptom);
		return "symptom/edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@Valid Symptom symptom, BindingResult result) {
		if (result.hasErrors()) {
			return "symptom/edit";
		}
		repository.save(symptom);

		return "redirect:/symptom/show/";
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(Long id) {
		Symptom symptom=repository.findOne(id);
		repository.delete(symptom);
	
		return "redirect:/symptom/show/";
	}

	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(Model model) {
		List<Symptom> list=repository.findAll();

		model.addAttribute("list",list);
	
		return "symptom/show";
	}

}
