package cors.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cors.domain.AdverseReaction;
import cors.domain.MedicalCase;
import cors.domain.Symptom;
import cors.repository.AdverseReactionRepository;
import cors.repository.MedicalCaseRepository;
import cors.repository.SymptomRepository;


@Controller
@RequestMapping("/adverseReaction")
public class AdverseReactionController {


	@Autowired
	private MedicalCaseRepository medicalCaseRepository;
	

	@Autowired
	private SymptomRepository symptomRepository;
	
	@Autowired
	private AdverseReactionRepository repository;
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(Long id, Long medicalCaseId,Model model) {
		AdverseReaction adverseReaction=null;
		if(id!=null){
			adverseReaction=repository.findOne(id);
		}else{
			MedicalCase medicalCase=medicalCaseRepository.findOne(medicalCaseId);
			adverseReaction=new AdverseReaction();
			adverseReaction.setMedicalCase(medicalCase);
		}
		model.addAttribute("adverseReaction",adverseReaction);
		
		List<Symptom> symptoms = symptomRepository.findAll();
		model.addAttribute("symptoms",symptoms);
		
		return "adverseReaction/edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@Valid AdverseReaction adverseReaction, BindingResult result) {
		if (result.hasErrors()) {
			return "adverseReaction/edit";
		}
		repository.save(adverseReaction);

		return "redirect:/medicalCase/show/" + adverseReaction.getMedicalCase().getId()+"#t4";
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(Long id) {
		AdverseReaction adverseReaction=repository.findOne(id);
		Long medicalCaseId=adverseReaction.getMedicalCase().getId();
		repository.delete(adverseReaction);
	
		return "redirect:/medicalCase/show/" + medicalCaseId+"#t4";
	}


}
