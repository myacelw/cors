package cors.domain.condition;

public interface ConditionType<T> {

	public String getName();
	
	public T getValue(Object v);
}
