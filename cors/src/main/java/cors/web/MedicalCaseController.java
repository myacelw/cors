package cors.web;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cors.domain.AdverseReaction;
import cors.domain.ClinicalDiagnosis;
import cors.domain.ConditionGroup;
import cors.domain.MedicalCase;
import cors.domain.TestResult;
import cors.domain.TestType;
import cors.domain.condition.type.DefaultConditionType;
import cors.repository.AdverseReactionRepository;
import cors.repository.ClinicalDiagnosisRepository;
import cors.repository.ConditionGroupRepository;
import cors.repository.MedicalCaseRepository;
import cors.repository.TestResultRepository;
import cors.service.ConditionTypes;
import cors.service.MedicalCaseService;

@Controller
@RequestMapping("/medicalCase")
public class MedicalCaseController {

	private final static String SQL="SELECT DISTINCT mc.*,cd.`name` clinical FROM medical_case mc "
			+ "LEFT JOIN clinical_diagnosis cd ON mc.clinical_id = cd.id "
			+ "LEFT JOIN test_result tr_Initial ON mc.id = tr_Initial.medical_case_id and tr_Initial.type=0 "
			+ "LEFT JOIN test_result tr_Hormone ON mc.id = tr_Hormone.medical_case_id and tr_Hormone.type=1 "
			+ "LEFT JOIN test_result tr_FK506 ON mc.id = tr_FK506.medical_case_id and tr_FK506.type=2 "
			+ "LEFT JOIN adverse_reaction ar ON ar.medical_case_id = mc.id "
			+ "WHERE 1=1 AND ";
	
	@Autowired
	private MedicalCaseService service;

	@Autowired
	private MedicalCaseRepository repository;

	@Autowired
	private TestResultRepository testResultRepository;

	@Autowired
	private AdverseReactionRepository adverseReactionRepository;

	@Autowired
	private ClinicalDiagnosisRepository clinicalDiagnosisRepository;

	@Autowired
	private ConditionGroupRepository conditionGroupRepository;
	
	@Autowired
	private ConditionTypes conditionTypes;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// @Autowired
	// private MedicalCaseQuery medicalCaseQuery;

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(Long id, Model model) {

		MedicalCase ca = null;
		if (id != null) {
			ca = repository.findOne(id);
		} else {
			ca = new MedicalCase();
		}

		List<ClinicalDiagnosis> clinicals = clinicalDiagnosisRepository
				.findAll();
		model.addAttribute("clinicals", clinicals);
		model.addAttribute("medicalCase", ca);
		return "medicalCase/edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@Valid MedicalCase ca, BindingResult result) {
		if (result.hasErrors()) {
			return "medicalCase/edit";
		}
		if (ca.getId() == null) {
			List<MedicalCase> list = repository.findByNumber(ca.getNumber());
			if (!list.isEmpty()) {
				ObjectError e = new FieldError("MedicalCase", "number",
						"病历号不唯一，请重新输入。");
				result.addError(e);
				return "medicalCase/edit";
			}
		} else {
			List<MedicalCase> list = repository.findByNumber(ca.getNumber());
			if (!(list.isEmpty() || (list.size() == 1 && list.get(0).getId() == ca
					.getId()))) {
				ObjectError e = new FieldError("MedicalCase", "number",
						"病历号不唯一，请重新输入。");
				result.addError(e);
				return "medicalCase/edit";
			}
		}

		repository.save(ca);
		return "redirect:/medicalCase/show/" + ca.getId();
	}

	@RequestMapping(value = "toNeg", method = RequestMethod.GET)
	public String setToNeg(Long id, TestType type, Model model) {
		MedicalCase ca = repository.findOne(id);
		model.addAttribute("medicalCase", ca);
		model.addAttribute("type", type);
		return "medicalCase/toNeg";
	}

	@RequestMapping(value = "toNeg", method = RequestMethod.POST)
	public String setToNeg(MedicalCase medicalCase, TestType type) {
		MedicalCase m = repository.findOne(medicalCase.getId());
		if (TestType.Hormone == type) {
			m.setHormoneToNeg(medicalCase.getHormoneToNeg());
		} else {
			m.setFk506ToNeg(medicalCase.getFk506ToNeg());
		}
		repository.save(m);
		return "redirect:/medicalCase/show/" + medicalCase.getId() + "#t3";
	}

	@RequestMapping(value = "hormoneReason", method = RequestMethod.GET)
	public String setHormoneReason(Long id, Model model) {
		MedicalCase m = repository.findOne(id);
		model.addAttribute("medicalCase", m);
		return "medicalCase/hormoneReason";
	}

	@RequestMapping(value = "hormoneReason", method = RequestMethod.POST)
	public String setHormoneReason(MedicalCase medicalCase) {
		MedicalCase m = repository.findOne(medicalCase.getId());
		m.setHormoneReason(medicalCase.getHormoneReason());
		repository.save(m);
		return "redirect:/medicalCase/show/" + m.getId() + "#t3";
	}

	@RequestMapping(value = "fk506Reason", method = RequestMethod.GET)
	public String setFK506(Long id, Model model) {
		MedicalCase m = repository.findOne(id);
		model.addAttribute("medicalCase", m);
		return "medicalCase/fk506Reason";
	}

	@RequestMapping(value = "fk506Reason", method = RequestMethod.POST)
	public String setFK506(MedicalCase medicalCase) {
		MedicalCase m = repository.findOne(medicalCase.getId());
		m.setFk506Reason(medicalCase.getFk506Reason());
		repository.save(m);
		return "redirect:/medicalCase/show/" + m.getId() + "#t3";
	}

	@RequestMapping(value = "find", method = RequestMethod.GET)
	public String find(MedicalCase ca, Model model) {
		List<ClinicalDiagnosis> clinicals = clinicalDiagnosisRepository
				.findAll();
		model.addAttribute("clinicals", clinicals);
		return "medicalCase/find";
	}

	@RequestMapping(value = "find", method = RequestMethod.POST)
	public String find(MedicalCase ca, BindingResult result, Model model) {
		Date d = ca.getFk506ToNeg();
		boolean unfk506toneg = d != null && d.after(new Date());
		boolean fk506toneg = d != null && !d.after(new Date());

		Map<String, Object> ps = new HashMap<String, Object>();

		List<MedicalCase> list = repository.findAll();

		for (Iterator<MedicalCase> iterator = list.iterator(); iterator
				.hasNext();) {
			MedicalCase medicalCase = iterator.next();
			if (fk506toneg && medicalCase.getFk506ToNeg() == null) {
				iterator.remove();
			}
			if (unfk506toneg && medicalCase.getFk506ToNeg() != null) {
				iterator.remove();
			}
			if (ca.getName() != null && !ca.getName().isEmpty()
					&& !ca.getName().equals(medicalCase.getName())) {
				iterator.remove();
			}
			if (ca.getNumber() != null && !ca.getNumber().isEmpty()
					&& !ca.getNumber().equals(medicalCase.getNumber())) {
				iterator.remove();
			}
			if (ca.getClinical() != null
					&& ca.getClinical().getId() != null
					&& (medicalCase.getClinical() == null || !ca.getClinical()
							.getId().equals(medicalCase.getClinical().getId()))) {
				iterator.remove();
			}
		}

		if (list.size() == 0) {
			ObjectError e = new FieldError("MedicalCase", "id",
					"没有找到对应的病例，请重新输入条件。");
			result.addError(e);
			return "medicalCase/find";
		}
		model.addAttribute("list", list);
		return "medicalCase/list";
	}

	@RequestMapping(value = "query", method = RequestMethod.GET)
	public String queryLink() {
		return "medicalCase/query";
	}

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public String query(ConditionGroup conditions, Model model) {
		conditions.initSql(conditionTypes);

		String sql = SQL + conditions.getSqlpart();

		System.out.println("===>>>" + conditions.getSqlinfostr());
		Object[] args = conditions.getArgs();
		int[] argtypes = conditions.getArgtypes();		
		
		List<Map> list = queryMC(sql, args, argtypes);
		model.addAttribute("list", list);
		return "tmpl-mustache::table_part";
	}

	private List<Map> queryMC(String sql, Object[] args, int[] argtypes){
		List<Map> list = jdbcTemplate.query(sql, args,argtypes
				, (rs, rowNum) -> {
					Map obj =new HashMap();
					int n =rs.getMetaData().getColumnCount();
					for (int j = 1; j <= n; j++) {
						String name = rs.getMetaData().getColumnLabel(j);
						if("fk506reason".equals(name)){
							name="fk506Reason";
						}
						if("fk506to_neg".equals(name)){
							name="fk506ToNeg";
						}
						String namestr = toUpper(name);
						Object value = rs.getString(j);
						int type =rs.getMetaData().getColumnType(j);
						if(Types.DATE == type || Types.TIMESTAMP == type){
							value = rs.getDate(j);
						}
						obj.put(namestr, value);
					}
					return obj;
					
//					MedicalCase t = new MedicalCase();
//					t.setId(rs.getLong("id"));
//					t.setNumber( rs.getString("number"));
//					t.setName( rs.getString("name"));
//					t.setSex(Sex.values()[ rs.getInt("sex")]);
//					t.setNation( rs.getString("nation"));
//					t.setBirthday( rs.getDate("birthday"));
//					ClinicalDiagnosis clinical = rs.getLong("clinical");
//
//					String pathology = rs.getString("pathology");
//
//					Date onsetTime = rs.getDate("onset_time");
//					Float weight = rs.getFloat("weight");
//					Date  = rs.getDate("hormoneToNeg");
//					String hormoneReason = rs.getString("hormoneReason");
//
//					Date  = rs.getDate("fk506ToNeg");
//
//					String fk506Reason = rs.getString("fk506Reason");

				});
		return list;
	}

	private String toUpper(String name) {
		StringBuffer ton = new StringBuffer();
		boolean b =false;
		char [] cs = name.toCharArray();
		for (int i = 0; i < cs.length; i++) {
			if(cs[i]=='_'){
				b=true;
			}else if(b){
				ton.append(Character.toUpperCase(cs[i]));
				b=false;
			}else{
				ton.append(cs[i]);
			}
		}
		String namestr = ton.toString();
		return namestr;
	}

	@RequestMapping(value = "condition_part")
	public String condition_part(Model model) {
		model.addAttribute("types", conditionTypes.types());
		return "tmpl-mustache::condition_part";
	}

	//查询类型下拉框
	@RequestMapping(value = "get_type_option/{name}", method = RequestMethod.GET)
	public String list(@PathVariable("name") String name, Model model) {
		for (Iterator<DefaultConditionType> iterator = conditionTypes.types()
				.iterator(); iterator.hasNext();) {
			DefaultConditionType c = iterator.next();
			if (c.getName().replace('.', '_').equals(name)) {
				model.addAttribute("options", c.getOptions());
				return "tmpl-mustache::select_part";
			}
		}
		model.addAttribute("options", new ArrayList());
		return "tmpl-mustache::select_part";

	}
	
	@RequestMapping(value = "saveQuery/{name}", method = RequestMethod.POST)
	@ResponseBody
	public String saveQuery(@PathVariable("name") String name, ConditionGroup conditions, Model model) {
		conditions.initSql(conditionTypes);
		conditions.setName(name);
		conditionGroupRepository.save(conditions);
		
		return "success";
	}
	
	@RequestMapping(value = "getQueryList", method = RequestMethod.GET)
	@ResponseBody
	public List<IdAndName> getQuery() {
		List<ConditionGroup> list=  conditionGroupRepository.findAll();
		List<IdAndName> result= new ArrayList<IdAndName>();
		for (ConditionGroup conditionGroup : list) {
			result.add(new IdAndName(conditionGroup.getId(),conditionGroup.getName()));
		}
		return result;
	}

	@RequestMapping(value = "query/{id}", method = RequestMethod.GET)
	public String query(@PathVariable("id") Long id,Model model) {
		ConditionGroup conditions=conditionGroupRepository.findOne(id);
		return query( conditions, model);
	}

	@RequestMapping(value = "deleteQuery/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String deleteQuery(@PathVariable("id") Long id) {
		conditionGroupRepository.delete(id);
		return "succsess";
	}
	
	@RequestMapping("/show/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		MedicalCase ca = repository.findOne(id);
		model.addAttribute("medicalCase", ca);

		List<TestResult> initialTestResults = testResultRepository
				.findByTypeAndMedicalCaseId(TestType.Initial, id);
		model.addAttribute("initialTestResults", initialTestResults);

		List<TestResult> hormoneTestResults = testResultRepository
				.findByTypeAndMedicalCaseId(TestType.Hormone, id);
		model.addAttribute("hormoneTestResults", hormoneTestResults);

		List<TestResult> fk506TestResults = testResultRepository
				.findByTypeAndMedicalCaseId(TestType.FK506, id);
		model.addAttribute("fk506TestResults", fk506TestResults);

		List<AdverseReaction> adverseReactions = adverseReactionRepository
				.findByMedicalCaseId(id);
		model.addAttribute("adverseReactions", adverseReactions);

		return "medicalCase/show";
	}

	@RequestMapping("/next/{id}")
	public String next(@PathVariable("id") Long id) {
		Iterable<MedicalCase> iter = repository.findAll();

		boolean find = false;

		Long firstId = null;

		for (Iterator<MedicalCase> iterator = iter.iterator(); iterator
				.hasNext();) {
			MedicalCase t = iterator.next();
			if (firstId == null) {
				firstId = t.getId();
			}
			if (id == -1L) {
				break;
			}
			if (find) {
				return "redirect:/medicalCase/show/" + t.getId();
			}
			if (t.getId().equals(id)) {
				find = true;
			}

		}
		return "redirect:/medicalCase/show/" + firstId;
	}
}
