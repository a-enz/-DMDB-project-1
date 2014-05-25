package ch.ethz.inf.dbproject.translator;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class MyDBFile {
	private String filename;
	private MyDBFileHeader header;
	private List<MyDBFileTuple> tuples;
	
	public MyDBFile(ResultSet set){
		
		try{
		    System.out.println(set.getMetaData());
		} catch (SQLException e) {
			
		}
	}
	
	public void setFileName(String name){
		filename = name;
	}

}
