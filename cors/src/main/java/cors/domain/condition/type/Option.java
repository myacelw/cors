package cors.domain.condition.type;

public class Option {

	private String name;
	
	private Object value;
	
	private String messageCode;

	public Option(String name,Object value){
		this.name=name;
		this.value = value;
	}
	
	public Option(String name,Object value,String messageCode){
		this.name=name;
		this.value = value;
		this.messageCode=messageCode;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	
	
}
