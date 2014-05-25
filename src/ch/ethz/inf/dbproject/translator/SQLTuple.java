package ch.ethz.inf.dbproject.translator;

import java.util.ArrayList;
import java.util.List;

public class SQLTuple {
	List<String> values;
	
	public SQLTuple(){
		this.values = new ArrayList<String>();
	}
	
	public void addValue(String val){
		values.add(val);
	}

}
