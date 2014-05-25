package ch.ethz.inf.dbproject.translator;

public class Column {
	private String name;
	private int size;
	private int typeCode;
	
	public Column(String name, int size, int code){
		this.name = name;
		this.size = size;
		this.typeCode = code;
	}

}
