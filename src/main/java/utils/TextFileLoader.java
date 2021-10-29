package utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import dto.TextDocumentData;

public class TextFileLoader {
	private final static String VALID_FILE_EXTENSION = ".txt";
	private final static String SEPARATION_CHARACTER = " ";
	private final static String EMPTY_DATA = "";
	
	private HashMap<String,LinkedList<Integer>> fileData ;
	private int actualElem = 0;
	
	public  List<TextDocumentData>loadFilesForFolder(final File folder) {
		List<TextDocumentData> documentsLoaded = new LinkedList<TextDocumentData>();
	   if (folder.isDirectory()) {
		    for (final File fileEntry : folder.listFiles()) {
		        if (fileEntry.isFile()){
		        	if( hasValidFileName(fileEntry.getName())) {
		        		documentsLoaded.add(loadFileData(fileEntry));
			        }		        	
		        } 
	        }
	    }
	   
	   return documentsLoaded;
	}
	
	private boolean hasValidFileName(String fileName){
		int lenght = fileName.length();
		if(lenght<4){
			return false;
		}
		String extension = fileName.substring(lenght-4, lenght);
		return VALID_FILE_EXTENSION.equalsIgnoreCase(extension);
	}
	
	private TextDocumentData loadFileData(final File fileToLoad){
		Path path = Paths.get(fileToLoad.getAbsolutePath());
		fileData = new HashMap<String,LinkedList<Integer>>() ;
        actualElem = 0;
       try {
            List<String> fileList = Files.readAllLines(path, StandardCharsets.UTF_8);
            for(String line:  fileList){
            	if(line !=null && !EMPTY_DATA.equals(line.trim())){
            		processLine( line);
            	}            	
            }
         } catch (IOException   e) {
            e.printStackTrace();
        }				
		TextDocumentData textData = new TextDocumentData(fileData, fileToLoad.getName());
		return textData;		
	}
	
	private int processLine(String line ){
		String[] words =line.split(SEPARATION_CHARACTER);
		if(words !=null && words.length >0){
			for(int i=0;i<words.length;i++){
				if(!fileData.containsKey(words[i])){			
					fileData.put(words[i], new LinkedList<Integer>());			
				}
				fileData.get(words[i]).add(actualElem);
				actualElem++;		
			}
		}
		return actualElem;
	}
	
	
}
