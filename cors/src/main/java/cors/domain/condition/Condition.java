package cors.domain.condition;

import cors.domain.condition.type.DefaultConditionType;

public class Condition {
	private DefaultConditionType type;
	
	private Operator operator;
	
	private Symbol symbol;
	
	private String value;

	private String selected;
	
	public DefaultConditionType getType() {
		return type;
	}

	public void setType(DefaultConditionType type) {
		this.type = type;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}

	public Object getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}
	
	public Object toValue(){
		if("id".equals(type.getClazz())){
			return type.toValue(this.selected);
		}
		return type.toValue(this.value);
	}
	
	
}
