package cors.domain.condition;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import cors.domain.condition.type.DefaultConditionType;
@Entity
public class ConditionItem {
	
	@Id
	@GeneratedValue
	private Long id;

	private String conditionTypeName;
	
	@Transient 
	private DefaultConditionType type;

	private Operator operator;

	private Symbol symbol;

	private String value;

	private String selected;
	
	@Transient 
	private String sqlpart;
	@Transient 
	private int sqlType;
	@Transient 
	private Object sqlObject;


	public Object toValue() {
		if ("id".equals(type.getClazz())) {
			return type.toValue(this.selected);
		}
		return type.toValue(this.value);
	}

	public void initSql() {	
		type.setSqlObjectAndType(this, "id".equals(type.getClazz())?selected:value);

		StringBuffer sb = new StringBuffer();
		
		sb.append(" ");
		if(operator!=null){
			sb.append(operator.getSql());
			sb.append(" ");
		}
		sb.append(type.getSql());
		sb.append(" ");
		sb.append(symbol.toName());
		sb.append(" ");
		sb.append("?");
		
		this.sqlpart=sb.toString(); 
	}
	
	public Object getSqlObject() {
		return symbol!=Symbol.like?sqlObject :"%"+((String)sqlObject)+"%";
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSqlPart() {
		return this.sqlpart;
	}

	
	public void setSqlType(int type) {
		this.sqlType = type;

	}

	public void setSqlObject(Object obj) {
		this.sqlObject = obj;
	}

	public int getSqlType() {
		return sqlType;
	}


	public DefaultConditionType getType() {
		return type;
	}


	public String getConditionTypeName() {
		return conditionTypeName;
	}

	public void setConditionTypeName(String conditionTypeName) {
		this.conditionTypeName = conditionTypeName;
	}

	public void setType(DefaultConditionType type) {
		this.type = type;
		this.conditionTypeName = type.getName();
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

	
	
	
}
