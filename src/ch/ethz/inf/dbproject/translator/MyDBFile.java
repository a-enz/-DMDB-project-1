package ch.ethz.inf.dbproject.translator;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class MyDBFile extends MyDBStructure {
	private String filename;
	private MyDBFileHeader header;
	private List<MyDBFileTuple> tuples;
	
	public MyDBFile(SQLFile file){
		this.filename = file.getName();
		this.header = new MyDBFileHeader(file.getMetaData());
		List<MyDBFileTuple> tpls = new ArrayList<MyDBFileTuple>();
		
		for(SQLTuple t : file.getPayLoad()){
			tpls.add(new MyDBFileTuple(t));
		}
		
		this.tuples = tpls;
	}
	
	public String toString(){
		String res = header.toString();
		for(MyDBFileTuple t : tuples){
			res += t.toString();
		}
		return res;
	}
}
