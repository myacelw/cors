package cors.domain.condition.type;

import cors.domain.condition.ConditionType;

public class IntegerConditionType implements ConditionType<Integer> {

	private String name;

	@Override
	public Integer getValue(Object v) {
		return Integer.valueOf(v.toString());
	}
	
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}




}
