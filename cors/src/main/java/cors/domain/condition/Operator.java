package cors.domain.condition;

public enum Operator {
	AND,OR,AND_NOT,OR_NOT;
	
	public String getSql(){
		return this.name().replace('_', ' ');
	}
	
}
