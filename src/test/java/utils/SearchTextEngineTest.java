package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import dto.SearchResult;
import dto.TextDocumentData;
import utils.commons.Utilities;


public class SearchTextEngineTest {
	
	private final static String WORDS_TO_SEARCH_ONE_RELEASE_NOTES = "Release notes from release V3";
	private final static String WORDS_TO_SEARCH_ALL_RELEASE_NOTES = "Release notes from release";

	private final static String WORDS_TO_SEARCH_COMPLEX_CASE = "illustrating techniques programmers can use to easily ";
	private final static String WORDS_TO_SEARCH_INVALID_VALUE = "Invalid Text To Find";
	private final static String WORDS_TO_SEARCH_INVALID_VALUE_ONE_MATCH_ON_ALL_TEXT = "Invalid Release Text To Find";
	private final static String WORDS_TO_SEARCH_INVALID_VALUE_ONE_MATCH_ON_ONE_DOC = "V3";
	
	private final static int  TOTAL_WORDS_RELEASE_NOTES = 5;
	private final static int  TOTAL_WORDS_RELEASE_INVALID_VAL_ONE_MATCH = 1;
	private final static int  TOTAL_WORDS_RELEASE_COMPLEX_CASE = 7;
	

	private final static String  CORRECT_FILE_RELEASE_NOTES_FILENAME = "ReleaseNotesV3.txt";
	private final static String  CORRECT_FILE_COMPLEX_CASE_FILENAME = "TDD.txt";
	
	
	private static String RESOURCES = System.getProperty("java.io.tmpdir") + File.separator
			+ System.currentTimeMillis();
	private static String ORIGINAL_RESORCES = null ;
	
	private final static String RELEASE_NOETES_PATH = "documentsReleaseNotes/ReleaseNotesV1.txt";
	private final static String GENERAL_DOCS_PATH = "generalDocumets/TDD.txt";
	
	private final SearchTextEngine searchEngine = new SearchTextEngine();
	
	public void setup(String resourcePath){
		RESOURCES = System.getProperty("java.io.tmpdir") + File.separator
				+ System.currentTimeMillis();
        File file = new File( ClassLoader.getSystemResource(resourcePath).getPath());
        // get the parent of the file
        ORIGINAL_RESORCES = file.getParent( );
	}
	
	@After
	public  void tearDown()  {
		// TODO: Check why had problems in order to delete files. in some test
		// css style files remain bloked and we cannot delete them
		File createdResources = new File(RESOURCES);
		if (createdResources.exists() && createdResources.isDirectory()) {
			createdResources.delete();
		}
	}
	
	@Test
	public void emptyFilename() {
		// having
		setup(RELEASE_NOETES_PATH);
		Utilities.copyTestFolder(ORIGINAL_RESORCES, RESOURCES);
		List<TextDocumentData> fileData = new ArrayList<TextDocumentData> ();
		// when
		List<SearchResult> resultData = searchEngine.searchText(fileData, WORDS_TO_SEARCH_ONE_RELEASE_NOTES);
		// then
		assertTrue("No result data to be found", resultData.isEmpty());
	}

	@Test
	public void emptyFileData() {
		// having
		List<TextDocumentData> fileData = new ArrayList<TextDocumentData> ();
		fileData.add(new TextDocumentData("testFile.txt"));
		// when
		List<SearchResult> resultData = searchEngine.searchText(fileData, "");
		// then
		assertTrue("No result data to be found", resultData.isEmpty());
	}
	
	@Test
	public void invaildSearch() {
		// having
		setup(RELEASE_NOETES_PATH);
		Utilities.copyTestFolder(ORIGINAL_RESORCES, RESOURCES);
		TextFileLoader loaderEngine = new TextFileLoader();
		List<TextDocumentData> fileData = loaderEngine.loadFilesForFolder(new File(RESOURCES));
		// when
		List<SearchResult> resultData = searchEngine.searchText(fileData, WORDS_TO_SEARCH_INVALID_VALUE);
		// then
		assertTrue("No result data to be found", resultData.isEmpty());
	}
	
	@Test
	public void findOneElementWithAllWords() {
		// having
		setup(RELEASE_NOETES_PATH);
		Utilities.copyTestFolder(ORIGINAL_RESORCES, RESOURCES);
		TextFileLoader loaderEngine = new TextFileLoader();
		List<TextDocumentData> fileData = loaderEngine.loadFilesForFolder(new File(RESOURCES));
		// when
		assertFalse("File Data must be loaded", fileData.isEmpty());
		List<SearchResult> resultData = searchEngine.searchText(fileData, WORDS_TO_SEARCH_ONE_RELEASE_NOTES);
		// then
		assertFalse("Result Data must be Informed", resultData.isEmpty());
		for(SearchResult result : resultData){
			if(CORRECT_FILE_RELEASE_NOTES_FILENAME.equals(result.getFileName())){
				assertEquals("Invalid word count", TOTAL_WORDS_RELEASE_NOTES, result.getElementsFound());
			}else{
				assertEquals("Invalid word count"+result.getFileName(), TOTAL_WORDS_RELEASE_NOTES-1, result.getElementsFound());
			}
		}
	}

	@Test
	public void findAllElementWithAllWords() {
		// having
		setup(RELEASE_NOETES_PATH);
		Utilities.copyTestFolder(ORIGINAL_RESORCES, RESOURCES);
		TextFileLoader loaderEngine = new TextFileLoader();
		List<TextDocumentData> fileData = loaderEngine.loadFilesForFolder(new File(RESOURCES));
		// when
		assertFalse("File Data must be loaded", fileData.isEmpty());
		List<SearchResult> resultData = searchEngine.searchText(fileData, WORDS_TO_SEARCH_ALL_RELEASE_NOTES);
		// then
		assertFalse("Result Data must be Informed", resultData.isEmpty());
		for(SearchResult result : resultData){
			assertEquals("Invalid word count", TOTAL_WORDS_RELEASE_NOTES-1, result.getElementsFound());
		}
	}

	@Test
	public void invalidSearch() {
		// having
		setup(RELEASE_NOETES_PATH);
		Utilities.copyTestFolder(ORIGINAL_RESORCES, RESOURCES);
		TextFileLoader loaderEngine = new TextFileLoader();
		List<TextDocumentData> fileData = loaderEngine.loadFilesForFolder(new File(RESOURCES));
		// when
		assertFalse("File Data must be loaded", fileData.isEmpty());
		List<SearchResult> resultData = searchEngine.searchText(fileData, WORDS_TO_SEARCH_INVALID_VALUE);
		// then
		assertTrue("Result Data must be Informed", resultData.isEmpty());
	}

	@Test
	public void invalidSearchOneMatchAllDocs() {
		// having
		setup(RELEASE_NOETES_PATH);
		Utilities.copyTestFolder(ORIGINAL_RESORCES, RESOURCES);
		TextFileLoader loaderEngine = new TextFileLoader();
		List<TextDocumentData> fileData = loaderEngine.loadFilesForFolder(new File(RESOURCES));
		// when
		assertFalse("File Data must be loaded", fileData.isEmpty());
		List<SearchResult> resultData = searchEngine.searchText(fileData, WORDS_TO_SEARCH_INVALID_VALUE_ONE_MATCH_ON_ALL_TEXT);
		// then
		assertFalse("Result Data must be Informed", resultData.isEmpty());
		for(SearchResult result : resultData){
			assertEquals("Invalid word count for complex case", TOTAL_WORDS_RELEASE_INVALID_VAL_ONE_MATCH, result.getElementsFound());
		}
	}
	
	@Test
	public void invalidSearchOneMatchOneDoc() {
		// having
		setup(RELEASE_NOETES_PATH);
		Utilities.copyTestFolder(ORIGINAL_RESORCES, RESOURCES);
		TextFileLoader loaderEngine = new TextFileLoader();
		List<TextDocumentData> fileData = loaderEngine.loadFilesForFolder(new File(RESOURCES));
		// when
		assertFalse("File Data must be loaded", fileData.isEmpty());
		List<SearchResult> resultData = searchEngine.searchText(fileData, WORDS_TO_SEARCH_INVALID_VALUE_ONE_MATCH_ON_ONE_DOC);
		// then
		assertFalse("Result Data must be Informed", resultData.isEmpty());
		for(SearchResult result : resultData){
			if(CORRECT_FILE_RELEASE_NOTES_FILENAME.equals(result.getFileName())){
				assertEquals("Invalid word count", 1,  result.getElementsFound());
			}else{
				assertEquals("Invalid word count"+result.getFileName(), 0, result.getElementsFound());
			}
		}
	}
	
	
	@Test
	public void findAComplexSearch() {
		// having
		tearDown();
		setup(GENERAL_DOCS_PATH);
		Utilities.copyFile(new File(ClassLoader.getSystemResource(GENERAL_DOCS_PATH).getPath()), RESOURCES);
		TextFileLoader loaderEngine = new TextFileLoader();
		List<TextDocumentData> fileData = loaderEngine.loadFilesForFolder(new File(RESOURCES));
		// when
		assertFalse("File Data must be loaded", fileData.isEmpty());
		List<SearchResult> resultData = searchEngine.searchText(fileData, WORDS_TO_SEARCH_COMPLEX_CASE);
		// then
		assertFalse("Result Data must be Informed", resultData.isEmpty());
		for(SearchResult result : resultData){
			if(CORRECT_FILE_COMPLEX_CASE_FILENAME.equals(result.getFileName())){
				assertEquals("Invalid word count for complex case", TOTAL_WORDS_RELEASE_COMPLEX_CASE, result.getElementsFound());
			}else{
				fail("Only one valid file must be loaded");
			}
		}
	}
	
}
