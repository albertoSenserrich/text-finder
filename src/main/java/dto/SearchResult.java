package dto;

public class SearchResult {
	private int wordLenght = 0;	
	private int elementsFound = 0;
	private int acceptanceRate = 0;
	private final String fileName;
	
	public SearchResult( int wordLenght,  int elementsFound, String fileName){
		this.wordLenght = wordLenght;	
		this.elementsFound = elementsFound;
		this.fileName = fileName;
	}
	
	public int getWordLenght() {
		return wordLenght;
	}
	
	public int getElementsFound() {
		return elementsFound;
	}

	public String getFileName() {
		return fileName;
	}

	public int getAcceptanceRate() {
		return acceptanceRate;
	}

	public void setAcceptanceRate(int acceptanceRate) {
		this.acceptanceRate = acceptanceRate;
	}

	@Override
	public String toString() {
		return "SearchResult [acceptanceRate=" + acceptanceRate + ", fileName=" + fileName + "]";
	}
	
	
}
