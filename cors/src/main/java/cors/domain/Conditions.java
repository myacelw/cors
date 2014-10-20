package cors.domain;

import java.util.ArrayList;
import java.util.List;

import cors.domain.condition.Condition;

public class Conditions {
	private List<Condition> conditions = new ArrayList<Condition>();

	public List<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}
	
	
	
}
