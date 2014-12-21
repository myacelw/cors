package cors.domain.condition;

public enum Symbol {
	// ���ڣ������ڣ����ڣ�С�ڣ����ڵ��ڣ�С�ڵ���,like
	eq("=") ,neq("!="),gt(">"),lt("<"),gte(">="),lte("<="),like("like");
	
	private String name;
	
	private Symbol(String name){
		this.name=name;
	}
	
	public String toName(){
		return this.name;
	}
	
}
