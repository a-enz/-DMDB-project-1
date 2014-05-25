package ch.ethz.inf.dbproject.translator;

import com.mysql.jdbc.ResultSetMetaData;
import java.sql.*;
import java.util.List;

public class SQLFile {
	
	private String filename;
	private List<Column> metaData;
	
	public SQLFile(String name){
		filename = name;
	}
	
	public void setMetaData(List<Column> meta){
		metaData = meta;
	}
}
