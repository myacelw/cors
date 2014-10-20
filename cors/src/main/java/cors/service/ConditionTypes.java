package cors.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cors.domain.ClinicalDiagnosis;
import cors.domain.Sex;
import cors.domain.Symptom;
import cors.domain.condition.type.DefaultConditionType;
import cors.domain.condition.type.Option;
import cors.repository.ClinicalDiagnosisRepository;
import cors.repository.SymptomRepository;

@Component
public class ConditionTypes {
	@Autowired
	private SymptomRepository symptomRepository;
	@Autowired
	private ClinicalDiagnosisRepository clinicalDiagnosisRepository;
	
	private List<DefaultConditionType> r ;
	
	public DefaultConditionType get(String name){
		for(DefaultConditionType t : types()){
			if(t.getName().equals(name)){
				return t;
			}
		}
		return null;
	}
	
	
	public List<DefaultConditionType> types(){
		if(r==null){
			r= new ArrayList<DefaultConditionType>();
			add("mc.number");
			add("mc.name");
			add("mc.birthday","date");
			add("mc.age","float","(datediff(sysdate(),mc.birthday)/365)");
			addEnum("mc.sex",Sex.values(),"Sex");
			add("mc.nation");
			
			addList("mc.clinical_id",clinicalDiagnosisRepository.findAll());
			add("mc.pathology");
			add("mc.onsetAge","float","(datediff(mc.onsetTime,mc.birthday)/365)");
			add("mc.weight","float");
			add("mc.hormoneToNegDay","int","datediff(mc.hormoneToNeg,mc.onsetTime)");
			add("mc.hormoneReason");
			add("mc.fk506ToNegDay","int","datediff(mc.fk506ToNeg,mc.onsetTime)");
			add("mc.fk506Reason");

			addTestResult("tr_Initial");

			add("tr_Hormone.name");
			add("tr_Hormone.startDateDay","int","datediff(tr_Hormone.startDate,mc.onsetTime)");
			add("tr_Hormone.dose");
			addTestResult("tr_Hormone");
			

			add("tr_FK506.startDateDay","int","datediff(tr_FK506.startDate,mc.onsetTime)");
			add("tr_FK506.dose","float");
			add("tr_FK506.hormoneDose");
			add("tr_FK506.concentration","float");
			addTestResult("tr_FK506");
			add("tr_FK506.wt","float");
			add("tr_FK506.bun","float");
			add("tr_FK506.urea","float");
			add("tr_FK506.mem");
			
			add("ar.reactionDateDay","int","datediff(ar.reactionDate,tr_FK506.startDate)");
			addList2("ar.symptom_id",symptomRepository.findAll());
			add("ar.mem");
		}
				
		return r;
	}
	
	private void addTestResult(String type){
		add(type+ ".urinepro");
		add(type+ ".rbc");
		add(type+ ".protein","float");
		add(type+ ".alb","float");
		add(type+ ".chol","float");
		add(type+ ".tg","float");
		add(type+ ".crea","float");
		add(type+ ".alt","float");
		add(type+ ".ast","float");
		add(type+ ".ccr","float");
	}
	
	private void add(String name){
		DefaultConditionType t  = new DefaultConditionType();
		t.setName(name);
		r.add(t);
	}
	
	private void add(String name,String clazz){
		DefaultConditionType t  = new DefaultConditionType();
		t.setName(name);
		t.setClazz(clazz);
		r.add(t);
	}
	
	private void add(String name,String clazz,String sql){
		DefaultConditionType t  = new DefaultConditionType();
		t.setName(name);
		t.setClazz(clazz);
		t.setSql(sql);
		r.add(t);
	}
	
	private void addList2(String name,List<Symptom> list){
		DefaultConditionType t  = new DefaultConditionType();
		t.setName(name);
		t.setClazz("id");
		List<Option> os = new ArrayList<Option>();
		for (int i = 0; i < list.size(); i++) {
			Option o = new Option(list.get(i).getName(),list.get(i).getId());
			os.add(o);
		}
		
		t.setOptions(os);
		r.add(t);
	}
	
	private void addList(String name,List<ClinicalDiagnosis> list){
		DefaultConditionType t  = new DefaultConditionType();
		t.setName(name);
		t.setClazz("id");
		List<Option> os = new ArrayList<Option>();
		for (int i = 0; i < list.size(); i++) {
			Option o = new Option(list.get(i).getName(),list.get(i).getId());
			os.add(o);
		}
		
		t.setOptions(os);
		r.add(t);
	}
	
	private void addEnum(String name,Enum[] es,String enumName){
		DefaultConditionType t  = new DefaultConditionType();
		t.setName(name);
		t.setClazz("id");
		List<Option> os = new ArrayList<Option>();
		for (int i = 0; i < es.length; i++) {
			Option o = new Option(es[i].name(),es[i].ordinal(),enumName+"."+es[i].name());
			os.add(o);
		}
		t.setOptions(os);
		r.add(t);
	}
	
	
	
	
}
