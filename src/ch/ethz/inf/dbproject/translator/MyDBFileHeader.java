package ch.ethz.inf.dbproject.translator;

import java.util.ArrayList;
import java.util.List;

public class MyDBFileHeader extends MyDBStructure {
	private char[] columns = new char[blocksize];
	private char[] columnSizes = new char[blocksize];
	private char[] columnTypes = new char[blocksize];
	
	public MyDBFileHeader(List<SQLColumn> metaData){
		
		List<String> names = new ArrayList<String>();
		List<String> sizes = new ArrayList<String>();
		List<String> types = new ArrayList<String>();
		
		for(SQLColumn c : metaData){
			names.add(c.getColumnName());
			sizes.add(Integer.toString(c.getSize()));
			types.add(Integer.toString(c.getTypeCode()));
		}
		
		writeBlock(names,columns);
		writeBlock(sizes,columnSizes);
		writeBlock(types,columnTypes);
	}
	
	public String toString(){
		String names = new String(columns);
		String sizes = new String(columnSizes);
		String types = new String(columnTypes);
		return names + sizes + types;
	}
	

}
