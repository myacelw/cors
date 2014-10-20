package cors.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class MedicalCase {

	@Id
	@GeneratedValue
	private Long id;
	@Length(min=3,max=20)
	private String number;
	@Length(min=1,max=50)
	private String name;
	@NotNull
	private Sex sex;
	
	private String nation;
	@NotNull
	private Date birthday;
	
	@ManyToOne
	private ClinicalDiagnosis clinical;
	
	private String pathology;
	
	private Date onsetTime;
	@Min(value=0)
	@Max(value=1000)
	private Float weight;
	
	
	private Date hormoneToNeg;
	
	private String hormoneReason;
	
	private String hormoneMem;
	
	private Date fk506ToNeg;
	
	private String fk506Reason;
	
	private String fk506Mem;
	
	
//	  @CreatedBy
//	  private User user;
//	  
//	  @CreatedDate
//	  private Date createdDate;
//
//	  @LastModifiedBy
//	  private Date modifiedDate;
	
	
	public Long getId() {
		return id;
	}
	public String getFk506Reason() {
		return fk506Reason;
	}
	public void setFk506Reason(String fk506Reason) {
		this.fk506Reason = fk506Reason;
	}
	public Date getHormoneToNeg() {
		return hormoneToNeg;
	}
	public void setHormoneToNeg(Date hormoneToNeg) {
		this.hormoneToNeg = hormoneToNeg;
	}
	public String getHormoneReason() {
		return hormoneReason;
	}
	public void setHormoneReason(String hormoneReason) {
		this.hormoneReason = hormoneReason;
	}
	public Date getFk506ToNeg() {
		return fk506ToNeg;
	}
	public void setFk506ToNeg(Date fk506ToNeg) {
		this.fk506ToNeg = fk506ToNeg;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public ClinicalDiagnosis getClinical() {
		return clinical;
	}
	public void setClinical(ClinicalDiagnosis clinical) {
		this.clinical = clinical;
	}
	public String getPathology() {
		return pathology;
	}
	public void setPathology(String pathology) {
		this.pathology = pathology;
	}
	public Date getOnsetTime() {
		return onsetTime;
	}
	public void setOnsetTime(Date onsetTime) {
		this.onsetTime = onsetTime;
	}
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	public String getHormoneMem() {
		return hormoneMem;
	}
	public void setHormoneMem(String hormoneMem) {
		this.hormoneMem = hormoneMem;
	}
	public String getFk506Mem() {
		return fk506Mem;
	}
	public void setFk506Mem(String fk506Mem) {
		this.fk506Mem = fk506Mem;
	}

}
