package cors.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cors.domain.ClinicalDiagnosis;
import cors.repository.ClinicalDiagnosisRepository;


@Controller
@RequestMapping("/clinical")
public class ClinicalDiagnosisController {


	@Autowired
	private ClinicalDiagnosisRepository repository;
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(Long id,Model model) {
		ClinicalDiagnosis clinical=null;
		if(id!=null){
			clinical=repository.findOne(id);
		}else{
			clinical=new ClinicalDiagnosis();
		}
		model.addAttribute("clinical",clinical);
		return "clinical/edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@Valid ClinicalDiagnosis clinical, BindingResult result) {
		if (result.hasErrors()) {
			return "clinical/edit";
		}
		repository.save(clinical);

		return "redirect:/clinical/show/";
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(Long id) {
		ClinicalDiagnosis clinical=repository.findOne(id);
		repository.delete(clinical);
	
		return "redirect:/clinical/show/";
	}

	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(Model model) {
		List<ClinicalDiagnosis> list=repository.findAll();

		model.addAttribute("list",list);
	
		return "clinical/show";
	}

}
