package cors.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class TestResult {
	@Id
	@GeneratedValue
	private Long id;
	
	 @ManyToOne
	private MedicalCase medicalCase ;
	
	private TestType type;
	
	private String name;
	
	private Date startDate;
	
	//起始剂量/剂量
	private Float dose;
	
	//激素剂量
	private String hormoneDose;
	
	//血药浓度
	private Float concentration; 
		
	//尿常规PRO
	private String urinepro;
	  
	private String rbc;
	
	//蛋白定量
	private Float protein;
	
	private Float alb;
	
	private Float chol;
	
	private Float tg;
	
	private Float crea;
	
	private Float alt;	
	
	private Float ast;	
	
	private Float ccr;	
	
	private Float wt;

	private Float bun;
	
	private Float urea;
	
	private String mem;
	
	public MedicalCase getMedicalCase() {
		return medicalCase;
	}

	public void setMedicalCase(MedicalCase medicalCase) {
		this.medicalCase = medicalCase;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Float getDose() {
		return dose;
	}

	public void setDose(Float dose) {
		this.dose = dose;
	}

	public String getHormoneDose() {
		return hormoneDose;
	}

	public void setHormoneDose(String hormoneDose) {
		this.hormoneDose = hormoneDose;
	}



	public Float getConcentration() {
		return concentration;
	}

	public void setConcentration(Float concentration) {
		this.concentration = concentration;
	}

	public String getUrinepro() {
		return urinepro;
	}

	public void setUrinepro(String urinepro) {
		this.urinepro = urinepro;
	}

	public String getRbc() {
		return rbc;
	}

	public void setRbc(String rbc) {
		this.rbc = rbc;
	}

	public Float getProtein() {
		return protein;
	}

	public void setProtein(Float protein) {
		this.protein = protein;
	}

	public Float getAlb() {
		return alb;
	}

	public void setAlb(Float alb) {
		this.alb = alb;
	}

	public Float getChol() {
		return chol;
	}

	public void setChol(Float chol) {
		this.chol = chol;
	}

	public Float getTg() {
		return tg;
	}

	public void setTg(Float tg) {
		this.tg = tg;
	}

	public Float getCrea() {
		return crea;
	}

	public void setCrea(Float crea) {
		this.crea = crea;
	}

	public Float getAlt() {
		return alt;
	}

	public void setAlt(Float alt) {
		this.alt = alt;
	}

	public Float getAst() {
		return ast;
	}

	public void setAst(Float ast) {
		this.ast = ast;
	}

	public Float getCcr() {
		return ccr;
	}

	public void setCcr(Float ccr) {
		this.ccr = ccr;
	}

	public Float getWt() {
		return wt;
	}

	public void setWt(Float wt) {
		this.wt = wt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TestType getType() {
		return type;
	}

	public void setType(TestType type) {
		this.type = type;
	}

	public Float getBun() {
		return bun;
	}

	public void setBun(Float bun) {
		this.bun = bun;
	}

	public Float getUrea() {
		return urea;
	}

	public void setUrea(Float urea) {
		this.urea = urea;
	}

	public String getMem() {
		return mem;
	}

	public void setMem(String mem) {
		this.mem = mem;
	}	
	
	
	
	
	
	
}
