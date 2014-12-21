package cors.domain;

public enum TestType {
	Initial,
	Hormone,
	FK506;
	
	public String messageCode(){
		return this.getClass().getSimpleName()+"."+this.name();
		
	}
	
}
