import java.io.File;
import java.util.List;
import java.util.Scanner;

import dto.SearchResult;
import dto.TextDocumentData;
import utils.SearchTextEngine;
import utils.SreenManager;
import utils.StatsEngine;
import utils.TextFileLoader;



public class Main {
	
	
	public static void main(String[] args){
		Main(args);
	}
	 
	protected static SreenManager interfaceManager = new SreenManager();
	protected static TextFileLoader loaderDataEngine = new TextFileLoader();
	protected static SearchTextEngine searchEngine = new SearchTextEngine();
	protected static StatsEngine StatsEngine = new StatsEngine();
	
	
	public static void Main(String[] args) {
		if (args.length == 0) {
			throw new IllegalArgumentException("No directory given to index.");
		}
		final File indexableDirectory = new File(args[0]);
		final List<TextDocumentData> fileData = loaderDataEngine.loadFilesForFolder(indexableDirectory);
		if(fileData.size()>0){
			String fs =  String.format(fileData.size()+" files read in directory"+ args[0]);
			System.out.println(fs);
			Scanner keyboard = new Scanner(System.in);
			boolean breakCondition = false;
			while ( !breakCondition ) {
				System.out.print("search> ");
				final String line = keyboard.nextLine();
				switch (line){
				case ":quit":
					breakCondition = true;
					break;
				default:
					List<SearchResult> foundData = searchEngine.searchText(fileData, line);
					StatsEngine.calculateStats(foundData);
					interfaceManager.printSearchResults(foundData);
				}
			}	
		}else{
			throw new IllegalArgumentException("No data to be load on selected directory");
		}
		
	}
}