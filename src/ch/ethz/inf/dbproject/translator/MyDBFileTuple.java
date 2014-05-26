package ch.ethz.inf.dbproject.translator;

import java.util.List;

public class MyDBFileTuple extends MyDBStructure {
	private char[] content = new char[blocksize];
	private List<Integer> columSizes;
	
	public MyDBFileTuple(SQLTuple t, List<Integer> sizes){
		this.columSizes = sizes;
		writeBlock(t.getValues(),content);
	}
	
	@Override
	public void writeBlock(List<String> values, char[] dest){
		int byteCount = 0;
		int loopCount = 0;
		
		//we hope 'values' doesn't contain more than 1024bytes of data
		for(String s : values){
	
			int length = s.length();
			
			int i = 0;
			for(; i < length; i++){
				dest[byteCount] = s.charAt(i);
				byteCount++;
			}
			
//			//This is for the version with comma separated stuff:			
//			loopCount++;
//			if(loopCount < values.size()){
//				dest[byteCount] = separator; //do we need to remove the comma at the end of all or do we not mind?
//				byteCount++;
//			}
			
			//This is the version where we pad the thing to column sizes
			int padEnd = byteCount + columSizes.get(loopCount) - i;
			for (int k = byteCount; k < padEnd;k++){
				dest[byteCount] = filler[0];
				byteCount++;
			}
			loopCount++;
			//end padding version
		}
		
		//now we fill the rest with escape characters
		for(int i = byteCount; i < blocksize; i++){
			dest[byteCount] = filler[0];
			byteCount++;
		}
	}
	
	public String toString(){
		return new String(content);
	}
}
