package cors.domain.condition.type;

import java.sql.Types;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import cors.DateFormatter;
import cors.domain.condition.ConditionItem;

public class DefaultConditionType{

	private String name;

	private String clazz="string";
	
	private String sql;
	
	private List<Option> options;
	
	public Object toValue(String v) {
		if (clazz.equals("int")){
			return Integer.valueOf(v);
		}else if (clazz.equals("float")){
			return Float.valueOf(v);
		}else if (clazz.equals("id")){
			return Integer.valueOf(v);
		}else if (clazz.equals("date")){
			try {
				return new DateFormatter().parse(v, Locale.getDefault());
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
		return v;
	}
	
	public void setSqlObjectAndType(ConditionItem c,String v) {
		if (clazz.equals("int")){
			c.setSqlType(Types.INTEGER);
			c.setSqlObject(Integer.valueOf(v));
		}else if (clazz.equals("float")){
			c.setSqlType(Types.FLOAT);
			c.setSqlObject(Float.valueOf(v));
		}else if (clazz.equals("id")){
			c.setSqlType(Types.INTEGER);
			c.setSqlObject(Integer.valueOf(v));
		}else if (clazz.equals("date")){
			try {
				c.setSqlType(Types.DATE);
				c.setSqlObject(new DateFormatter().parse(v, Locale.getDefault()));
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}else{
			c.setSqlType(Types.VARCHAR);
			c.setSqlObject(v);
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public String getSql() {
		return sql==null?name:sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

}
