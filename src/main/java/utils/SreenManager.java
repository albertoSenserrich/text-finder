package utils;

import java.util.List;

import dto.SearchResult;

public class SreenManager {
	
	//Text constants for case OK
	private final static String OUTPUT_TEXT_ACCEPTANCE_UNIT = "%";
	private final static String MIDDLE_BLOCK_TEXT = " : ";
	private final static String EMPTY_SPACES_BLOCK = "                   ";

	//Text constants for case No data found	
	private final static String NO_MATCHES_FOUND = "no matches found";
	
	
	public void printSearchResults(List<SearchResult> fileDataElements){
		if(!fileDataElements.isEmpty()){
			for(SearchResult elem: fileDataElements){
				StringBuffer stb = new StringBuffer();
				stb.append(elem.getFileName());
				stb.append(EMPTY_SPACES_BLOCK.substring(0, EMPTY_SPACES_BLOCK.length()-elem.getFileName().length()));
				stb.append(MIDDLE_BLOCK_TEXT);
				stb.append(elem.getAcceptanceRate());
				stb.append(OUTPUT_TEXT_ACCEPTANCE_UNIT);
				System.out.println(stb.toString());
			}	
		}else{
			System.out.println(NO_MATCHES_FOUND);
		}
	}
}
