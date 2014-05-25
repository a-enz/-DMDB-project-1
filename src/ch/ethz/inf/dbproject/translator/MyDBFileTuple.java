package ch.ethz.inf.dbproject.translator;

public class MyDBFileTuple extends MyDBStructure {
	private char[] content = new char[blocksize];
	
	public MyDBFileTuple(SQLTuple t){
		writeBlock(t.getValues(),content);
	}
	
	public String toString(){
		return new String(content);
	}
}
