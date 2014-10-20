package cors.domain.condition.type;

import cors.domain.condition.ConditionType;

public class StringConditionType implements ConditionType<String> {

	private String name;

	@Override
	public String getValue(Object v) {
		return v!=null?v.toString():"";
	}
	
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}




}
