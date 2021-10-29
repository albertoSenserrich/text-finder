package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dto.TextDocumentData;
import utils.commons.Utilities;


public class TextFileLoaderTest {

	
	private static final String RESOURCES = System.getProperty("java.io.tmpdir") + File.separator
			+ System.currentTimeMillis();
	private static String ORIGINAL_RESORCES = null ;
	private final static int TOTAL_DOCUMENTS_TO_LOAD = 6;
	
	@Before
	public void setup(){
        File file = new File( ClassLoader.getSystemResource("generalDocumets/AgileManifesto.txt").getPath());
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
	public void noDataToProcess() {
		// having
		tearDown();
		// when
		TextFileLoader loaderEngine = new TextFileLoader();
		List<TextDocumentData> fileData = loaderEngine.loadFilesForFolder(new File(System.getProperty("java.io.tmpdir") + File.separator+ System.currentTimeMillis()));
		// then
		assertTrue("No data is expected to be loaded", fileData.isEmpty());
	}
	
	@Test
	public void dataToProcess() {
		// having		
		Utilities.copyTestFolder(ORIGINAL_RESORCES, RESOURCES );
		// when
		TextFileLoader loaderEngine = new TextFileLoader();
		List<TextDocumentData> fileData = loaderEngine.loadFilesForFolder(new File(RESOURCES));
		// then
		assertEquals("Invlaid path no file data can be loaded", TOTAL_DOCUMENTS_TO_LOAD, fileData.size());
		for(TextDocumentData elemData : fileData){
			assertFalse("Data expected to be loaded",elemData.getWords().isEmpty());
		}
	}

}
