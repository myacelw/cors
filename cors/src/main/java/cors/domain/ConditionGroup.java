package cors.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cors.domain.condition.ConditionItem;
import cors.domain.condition.Symbol;
import cors.service.ConditionTypes;
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ConditionGroup {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;

	
	@OneToMany(cascade={CascadeType.ALL})
	private List<ConditionItem> conditionList = new ArrayList<ConditionItem>();

	public List<ConditionItem> getConditions() {
		return conditionList;
	}

	public void setConditions(List<ConditionItem> conditions) {
		this.conditionList = conditions;
	}
	
	@Transient 
	private String sqlpart;
	@Transient 
	private String sqlinfostr;
	@Transient 
	private List<Object> args;
	@Transient 
	private List<Integer> argtypes ;
	
	public void initSql(ConditionTypes types){
		StringBuffer sb = new StringBuffer();
		StringBuffer sql = new StringBuffer();

		args = new ArrayList<Object>();
		argtypes = new ArrayList<Integer>();
		
		for (Iterator<ConditionItem> iterator = conditionList.iterator(); iterator.hasNext();) {
			ConditionItem c = iterator.next();
			if(c.getSymbol()==null){
				iterator.remove();
			}else{
				if(c.getType()==null){
					c.setType(types.get(c.getConditionTypeName()));
				}
			
				appendSb(sb, c);
				appendSql(c,sql,args,argtypes);
			}
		}
		
		sqlpart = sql.toString();
		sqlinfostr = sb.toString();
	}
	
	private void appendSb(StringBuffer sb, ConditionItem c) {
		sb.append(c.getOperator()==null?"":c.getOperator().getSql());
		sb.append(" ");
		sb.append(c.getType().getSql());
		sb.append(" ");
		sb.append(c.getSymbol().toName());
		sb.append(" ");
		Object v = c.toValue();
		if(c.getSymbol()==Symbol.like){
			v= "%"+((String)v)+"%";
		}
		if(v instanceof String){
			sb.append("'");
			sb.append(v);
			sb.append("'");
		}else if(v instanceof Date){
			sb.append("str_to_date('");
			sb.append(v);
			sb.append("','%Y-%m-%d')");
		}else{
			sb.append(v);
		}
		sb.append(" ");
	}
	
	private void appendSql(ConditionItem c,StringBuffer sql,List<Object> args,List<Integer> argtypes ) {
		c.initSql();
		sql.append(c.getSqlPart());
		args.add(c.getSqlObject());
		argtypes.add(c.getSqlType());
	}
	

	private int[] toInts(List<Integer> argtypes) {
		int[] a = new int[argtypes.size()];
		int i=0;
		for (Integer t:argtypes) {
			a[i++] =t;
			
		}
		return a;
	}

	public String getSqlpart() {
		return sqlpart;
	}

	public String getSqlinfostr() {
		return sqlinfostr;
	}

	public Object[] getArgs() {
		return args.toArray();
	}

	public int[] getArgtypes() {
		return  toInts(argtypes);
	}
	
		public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
	
}
