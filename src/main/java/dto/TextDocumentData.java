package dto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class TextDocumentData  {

	private   HashMap<String,LinkedList<Integer>> words = new HashMap<String,LinkedList<Integer>>();
	private   String fileName;

	
	public TextDocumentData(HashMap<String,LinkedList<Integer>> words, String fileName){
		this.words = words;
		this.fileName = fileName;
	}
	
	public TextDocumentData( String fileName){
		this.fileName = fileName;
	}
	
	public HashMap<String, LinkedList<Integer>> getWords() {
		return words;
	}

	public String getFileName() {
		return fileName;
	}
	
	public boolean hasValidDataToProcess(){
		return words.size()>0;
	}

    
}
