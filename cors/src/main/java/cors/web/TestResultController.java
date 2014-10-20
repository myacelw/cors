package cors.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cors.domain.MedicalCase;
import cors.domain.TestResult;
import cors.domain.TestType;
import cors.repository.MedicalCaseRepository;
import cors.repository.TestResultRepository;
import cors.service.TestResultService;


@Controller
@RequestMapping("/testResult")
public class TestResultController {

	@Autowired
	private TestResultService service;

	@Autowired
	private MedicalCaseRepository medicalCaseRepository;
	
	@Autowired
	private TestResultRepository repository;
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(Long id, Long medicalCaseId,TestType type, Model model) {
		TestResult testResult=null;
		if(id!=null){
			testResult=repository.findOne(id);
		}else{
			MedicalCase medicalCase=medicalCaseRepository.findOne(medicalCaseId);
			testResult=new TestResult();
			testResult.setMedicalCase(medicalCase);
			testResult.setType(type);
		}
		model.addAttribute("testResult",testResult);
		return "testResult/edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@Valid TestResult testResult, BindingResult result) {
		if (result.hasErrors()) {
			return "testResult/edit";
		}
		repository.save(testResult);

		return "redirect:/medicalCase/show/" + testResult.getMedicalCase().getId()+"#t1";
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(Long id) {
		TestResult testResult=repository.findOne(id);
		Long medicalCaseId=testResult.getMedicalCase().getId();
		repository.delete(testResult);
	
		return "redirect:/medicalCase/show/" + medicalCaseId+"#t2";
	}


}
