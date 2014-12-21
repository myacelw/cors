package cors.domain.condition;

public enum Symbol {
	// 等于，不等于，大于，小于，大于等于，小于等于,like
	eq("=") ,neq("!="),gt(">"),lt("<"),gte(">="),lte("<="),like("like");
	
	private String name;
	
	private Symbol(String name){
		this.name=name;
	}
	
	public String toName(){
		return this.name;
	}
	
}
