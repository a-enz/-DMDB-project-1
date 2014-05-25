package ch.ethz.inf.dbproject.translator;

import java.util.List;


public abstract class MyDBStructure {
	
	public static final int blocksize = 1024;
	
	
//	public String escSequence(int length){
//		String seq = "\\";
//		seq = new String(new char[length]).replace("\0", seq);
//		return seq;
//	}
	
	public void writeBlock(List<String> values, char[] dest){
		int byteCount = 0;
		
		//we hope 'values' doesn't contain more than 1024bytes of data
		for(String s : values){
			
			int length = s.length();
			
			for(int i = 0; i < length; i++){
				dest[byteCount] = s.charAt(i);
				byteCount++;
			}
			
			dest[byteCount] = ','; //do we need to remove the comma at the end of all or do we not mind?
			byteCount++;
		}
		
		//now we fill the rest with escape characters
		for(int i = byteCount; i < blocksize; i++){
			dest[byteCount] = '\\';
			byteCount++;
		}
	}
	
}
