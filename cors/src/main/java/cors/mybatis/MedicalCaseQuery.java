package cors.mybatis;

import java.util.List;
import java.util.Map;

public interface MedicalCaseQuery {
	List<Map<String ,Object>> query(Map<String,Object> params);
}
