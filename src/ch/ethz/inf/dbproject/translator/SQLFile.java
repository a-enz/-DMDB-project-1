package ch.ethz.inf.dbproject.translator;

import com.mysql.jdbc.ResultSetMetaData;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLFile {
	
	private String filename;
	private List<SQLColumn> metaData;
	private List<SQLTuple> payLoad;
	
	public SQLFile(String name){
		filename = name;
		this.payLoad = new ArrayList<SQLTuple>();
	}
	
	public void setMetaData(List<SQLColumn> meta){
		metaData = meta;
	}
	
	public void addTuple(SQLTuple tuple){
		payLoad.add(tuple);
	}
	
	public List<SQLColumn> getMetaData(){
		return this.metaData;
	}
	
	public String getName(){
		return this.filename;
	}
	
	public List<SQLTuple> getPayLoad(){
		return this.payLoad;
	}
	
	
	
}
