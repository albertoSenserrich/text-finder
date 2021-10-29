package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import dto.SearchResult;


public class StatsEngineTest {

	
	private final StatsEngine statsEngine = new StatsEngine();
	private final static int DATA_TO_FOUNDLENGHT = 5;
	private final static int NO_DATA_FOUND = 0;
	
	
	@Test
	public void noDataToProcess() {
		// having
		List<SearchResult> resultData = new ArrayList<SearchResult> ();
		// when
		statsEngine.calculateStats(resultData);
		// then
		assertTrue("ResultData must be empty",resultData.isEmpty());
	}
	
	@Test
	public void noDataWithValidResultToProcess() {
		// having
		List<SearchResult> resultData = new ArrayList<SearchResult> ();
		resultData.add(new SearchResult(DATA_TO_FOUNDLENGHT, NO_DATA_FOUND, String.valueOf((new Date().getTime()))));
		resultData.add(new SearchResult(DATA_TO_FOUNDLENGHT, NO_DATA_FOUND, String.valueOf((new Date().getTime()))));
		resultData.add(new SearchResult(DATA_TO_FOUNDLENGHT, NO_DATA_FOUND, String.valueOf((new Date().getTime()))));
		resultData.add(new SearchResult(DATA_TO_FOUNDLENGHT, NO_DATA_FOUND, String.valueOf((new Date().getTime()))));
		// when
		statsEngine.calculateStats(resultData);
		// then
		assertTrue("ResultData must be empty",resultData.isEmpty());	
	}

	@Test
	public void filterElementsDontApplyIfDontNeeded() {
		// having
		List<SearchResult> resultData = new ArrayList<SearchResult> ();
		Random r = new Random();
		int elementsToBeFound =  (r.nextInt((DATA_TO_FOUNDLENGHT - 1) + 1) + 1);
		for(int i=0 ; i<elementsToBeFound ;i++){
			resultData.add(new SearchResult(DATA_TO_FOUNDLENGHT, (r.nextInt((DATA_TO_FOUNDLENGHT - 1) + 1) + 1), String.valueOf((new Date().getTime()))));
		}
		// when
		statsEngine.calculateStats(resultData);
		// then
		assertFalse("ResultData must be empty",resultData.isEmpty());	
		assertEquals("Only 10 lemts can be found as resultSet",resultData.size(), elementsToBeFound);	
	}
	
	@Test
	public void filterElementsDontNeeded() {
		// having
		List<SearchResult> resultData = new ArrayList<SearchResult> ();
		Random r = new Random();
		for(int i=0 ; i<20 ;i++){
			resultData.add(new SearchResult(DATA_TO_FOUNDLENGHT, (r.nextInt((DATA_TO_FOUNDLENGHT - 1) + 1) + 1), String.valueOf((new Date().getTime()))));
		}
		// when
		statsEngine.calculateStats(resultData);
		// then
		assertFalse("ResultData must be empty",resultData.isEmpty());	
		assertEquals("Only 10 lemts can be found as resultSet",resultData.size(), 10);	
	}

	private final static String FIRST_ELEMENT_FILENAME = "first";
	private final static String SECOND_ELEMENT_FILENAME = "second";
	private final static String THIRD_ELEMENT_FILENAME = "third";
	
	@Test
	public void orderIsOk() {
		// having
		List<SearchResult> resultData = new ArrayList<SearchResult> ();
		resultData.add(new SearchResult(DATA_TO_FOUNDLENGHT, 5 , FIRST_ELEMENT_FILENAME));
		resultData.add(new SearchResult(DATA_TO_FOUNDLENGHT, 4 , SECOND_ELEMENT_FILENAME));
		resultData.add(new SearchResult(DATA_TO_FOUNDLENGHT, 3 , THIRD_ELEMENT_FILENAME));
		for(int i=0 ; i<20 ;i++){
			resultData.add(new SearchResult(DATA_TO_FOUNDLENGHT, 2, String.valueOf((new Date().getTime()))));
		}
		
		// when
		statsEngine.calculateStats(resultData);
		// then
		assertFalse("ResultData must be empty",resultData.isEmpty());	
		assertEquals("Unexpected result order for element "+resultData.get(0), FIRST_ELEMENT_FILENAME, resultData.get(0).getFileName());
		assertEquals("Unexpected result order for element "+resultData.get(1), SECOND_ELEMENT_FILENAME, resultData.get(1).getFileName());
		assertEquals("Unexpected result order for element "+resultData.get(2), THIRD_ELEMENT_FILENAME, resultData.get(2).getFileName());
	}

	@Test
	public void testAccenceRatecalculation() {
		// having
		List<SearchResult> resultData = new ArrayList<SearchResult> ();
		resultData.add(new SearchResult(6, 6 , FIRST_ELEMENT_FILENAME));
		resultData.add(new SearchResult(6, 3 , SECOND_ELEMENT_FILENAME));
		resultData.add(new SearchResult(0, 0 , THIRD_ELEMENT_FILENAME));
		// when
		statsEngine.calculateStats(resultData);
		// then
		assertFalse("ResultData cannot be empty",resultData.isEmpty());	
		assertEquals("Unexpected result order for element "+resultData.get(0), 100, resultData.get(0).getAcceptanceRate());
		assertEquals("Unexpected result order for element "+resultData.get(1), 50, resultData.get(1).getAcceptanceRate());
		assertEquals("Unexpected result order for element "+resultData.get(2), 0, resultData.get(2).getAcceptanceRate());
	}
}
