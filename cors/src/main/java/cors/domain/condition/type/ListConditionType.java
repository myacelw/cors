package cors.domain.condition.type;

import java.util.ArrayList;
import java.util.List;

import cors.domain.condition.ConditionType;

public class ListConditionType<T> implements ConditionType<T> {

	private String name;
	
	private List<T> options = new ArrayList<T>();

	@Override
	public T getValue(Object v) {
		return (T)v;
	}
	
	public List<T> getOptions() {
		return options;
	}

	public void setOptions(List<T> options) {
		this.options = options;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}




}
