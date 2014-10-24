package cors.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//“Ï≥£∑¥”¶
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AdverseReaction {
	@Id
	@GeneratedValue
	private Long id;
	
	 @ManyToOne
	private MedicalCase medicalCase ;
	 
	private Date reactionDate;
	
	 @ManyToOne
	private Symptom symptom;
		
	private String mem;

	public Date getReactionDate() {
		return reactionDate;
	}

	public void setReactionDate(Date reactionDate) {
		this.reactionDate = reactionDate;
	}

	public Symptom getSymptom() {
		return symptom;
	}

	public void setSymptom(Symptom symptom) {
		this.symptom = symptom;
	}

	public String getMem() {
		return mem;
	}

	public void setMem(String mem) {
		this.mem = mem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MedicalCase getMedicalCase() {
		return medicalCase;
	}

	public void setMedicalCase(MedicalCase medicalCase) {
		this.medicalCase = medicalCase;
	}
	
	
}
