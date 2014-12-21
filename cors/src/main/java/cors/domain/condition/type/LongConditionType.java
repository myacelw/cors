package cors.domain.condition.type;

import cors.domain.condition.ConditionType;

public class LongConditionType implements ConditionType<Long> {

	private String name;

	@Override
	public Long getValue(Object v) {
		return Long.valueOf(v.toString());
	}
	
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}




}
