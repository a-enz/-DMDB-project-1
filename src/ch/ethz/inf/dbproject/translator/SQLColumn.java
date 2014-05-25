package ch.ethz.inf.dbproject.translator;

public class SQLColumn {
	private String name;
	private int size;
	private int typeCode;
	
	public SQLColumn(String name, int size, int code){
		this.name = name;
		this.size = size;
		this.typeCode = code;
	}
	
	public String getColumnName(){
		return this.name;
	}

}
