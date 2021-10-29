package utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dto.SearchResult;
import dto.TextDocumentData;

public class SearchTextEngine {

	public List<SearchResult> searchText(List<TextDocumentData> fileDataElements, String text){
		List<SearchResult> result = new LinkedList<SearchResult>();
		boolean found = false;
		List<String> wordsToSearch = formatWordsToSearch(text); 
		for(TextDocumentData elem : fileDataElements){
			SearchResult searchResult = findResults(elem, wordsToSearch);
			if(searchResult.getElementsFound()>0)
				found=  true;
			result.add(searchResult);
		}
		
		if(!found)
			result = new LinkedList<SearchResult>();
		return result;
	}
	
	protected List<String> formatWordsToSearch(String originalWord){
		List<String> rsl = new ArrayList<String>();
		if(rsl != null &&  !"".equals(originalWord.trim())){
			String[] wordsToSearch = originalWord.split(" ");
			if(wordsToSearch.length>0){
				for(String word: wordsToSearch){
					word = word.trim();
					if(!"".equals(word))
						rsl.add(word);
				}
			}
		}
		return rsl;
	}
	
	protected SearchResult findResults(TextDocumentData sourceData, List<String> wordsToSearch){
		int maxElemsFound = 0;
		final int maxElems = wordsToSearch.size();
		if(sourceData.getWords().size() >0 && wordsToSearch.size()>0){
			for(int i= 0 ;   i< wordsToSearch.size() ; i++){
				if(sourceData.getWords().containsKey(wordsToSearch.get(i))){
					LinkedList<Integer> wordsTOSearch = sourceData.getWords().get(wordsToSearch.get(i));
					if(!wordsToSearch.isEmpty() ){
							for(Integer wordPosition: wordsTOSearch){
								int newDeep = findResults( sourceData,  wordsToSearch.subList(i+1, wordsToSearch.size()),  1 ,  wordPosition+1, wordsToSearch.size());
								if(newDeep>maxElemsFound){
									maxElemsFound = newDeep;
								}
							}	
					}else{
						maxElemsFound++;
					}
					if(maxElemsFound == maxElems){
						break;
					}
				}
			}	
		}
		return new SearchResult(wordsToSearch.size(), maxElemsFound, sourceData.getFileName());
	}
	
	private int findResults(TextDocumentData sourceData, List<String> wordsToSearch, int actualDeep , int posToLookUp, int originalElems){
		int maxDeep = actualDeep;
		if(!wordsToSearch.isEmpty() && sourceData.getWords().containsKey(wordsToSearch.get(0))){
			actualDeep++;
			maxDeep = actualDeep;
			LinkedList<Integer> positionss = sourceData.getWords().get(wordsToSearch.get(0)); 
			if(!wordsToSearch.isEmpty()){
				for(Integer elem : positionss){
					if(elem == posToLookUp){
						int newDeep = findResults( sourceData,  wordsToSearch.subList(1, wordsToSearch.size()),  actualDeep ,  elem+1, originalElems);
						//wordsToSearch.size()-(originalElems-actualDeep)
						if(newDeep>maxDeep){
							maxDeep = newDeep;
						}
					}
				}		
			}
		}
		return maxDeep;
	}
	
	
	
}
