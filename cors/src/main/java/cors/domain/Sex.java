package cors.domain;

public enum Sex {
	male,female;
	
	public String messageCode(){
		return this.getClass().getSimpleName()+"."+this.name();
	}
	
}
