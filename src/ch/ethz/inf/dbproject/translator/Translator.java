package ch.ethz.inf.dbproject.translator;

import java.sql.*; 
import java.util.ArrayList;
import java.util.List;

import ch.ethz.inf.dbproject.database.MySQLConnection;
import java.io.*;


public class Translator {
	
	private Connection sqlConnection;
	private String[] tables;
	private final String filePath;
	private final String ext;
	
	public Translator(Connection c, String[] t, String path, String ext){
		sqlConnection = c;
		tables = t;
		filePath = path;
		this.ext = ext;
	}
	
	public void translate(){
		SQLFile sqlFile;
		
		//do for all tables
		for(String table : tables){
			sqlFile = readSQLTable(table);
			
			String f = filePath + table + ext;
			File file = new File(f);
			
			try{
				if(file.exists()) file.delete();
				
				PrintWriter out = new PrintWriter(new FileWriter(f,true));
				//translate stuff
				out.close();
				
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}

	
	//read a whole table into SQLFile
	public SQLFile readSQLTable(String table){
		//set filename:
		SQLFile sqlFile = new SQLFile(table);
		
		try {
			Statement stmt = sqlConnection.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * " +
				      		   				  "FROM " + table);
			
			ResultSetMetaData metaRes = res.getMetaData();
			System.out.println("reading metadata");
			List<Column> columnMeta = readMetaData(metaRes);
			
			sqlFile.setMetaData(columnMeta);
			
			
			
			
			return null;
			
			
		} catch (final SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	private List<Column> readMetaData(ResultSetMetaData res){
		try{
			int columnCount = res.getColumnCount();
			List<Column> metaData = new ArrayList<Column>();
			
			for(int i = 1; i < columnCount; i++){
				String name = res.getColumnName(i);
				int size = res.getColumnDisplaySize(i);
				int typeCode = res.getColumnType(i);
				
				metaData.add(new Column(name, size, typeCode));
				System.out.println(name  + " " + size + " " + typeCode);
			}
			return metaData;
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
}
