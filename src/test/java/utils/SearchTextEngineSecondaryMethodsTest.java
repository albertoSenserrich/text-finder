package utils;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class SearchTextEngineSecondaryMethodsTest {

	
	private final static String WORDS_TO_SEARCH_EMPTY = "";
	private final static String WORDS_TO_SEARCH_ONE_WORD = "class";
	private final static String WORDS_TO_SEARCH_MULTIPLE = " to be or not to be ";
	private final static String WORDS_TO_SEARCH__MULTIPLE_SPACE = " to be or   not to be ";
	
	private final static int COUNT_NO_WORDS_TO_PROCCES = 0;
	private final static int COUNT_ONE_WORD_TO_PROCCES = 1;
	private final static int COUNT_MULTIPLE_WORDS_TO_PROCCES = 6;
	
	private final static String ERROR_MESSAGE_INVALID_WORD_COUNT = "Invlaid path no file data can be loaded";
	
	
	@Test
	public void noWordsProcess() {
		// having
		// when
		SearchTextEngine  searchEngine = new SearchTextEngine ();
		List<String> wordsFOund = searchEngine.formatWordsToSearch(WORDS_TO_SEARCH_EMPTY);
		// then
		assertEquals(ERROR_MESSAGE_INVALID_WORD_COUNT, wordsFOund.size() , COUNT_NO_WORDS_TO_PROCCES);
	}
	
	@Test
	public void oneWordtoProcess() {
		// having
		// when
		SearchTextEngine  searchEngine = new SearchTextEngine ();
		List<String> wordsFOund = searchEngine.formatWordsToSearch(WORDS_TO_SEARCH_ONE_WORD);
		// then
		assertEquals(ERROR_MESSAGE_INVALID_WORD_COUNT, wordsFOund.size(), COUNT_ONE_WORD_TO_PROCCES);
	}

	@Test
	public void multipleWordtoProcess() {
		// having
		// when
		SearchTextEngine  searchEngine = new SearchTextEngine ();
		List<String> wordsFOund = searchEngine.formatWordsToSearch(WORDS_TO_SEARCH_MULTIPLE);
		// then
		assertEquals(ERROR_MESSAGE_INVALID_WORD_COUNT, wordsFOund.size() , COUNT_MULTIPLE_WORDS_TO_PROCCES);
	}
	
	@Test
	public void multipleWordtoProcessWithExtraSpaces() {
		// having
		// when
		SearchTextEngine  searchEngine = new SearchTextEngine ();
		List<String> wordsFOund = searchEngine.formatWordsToSearch(WORDS_TO_SEARCH__MULTIPLE_SPACE);
		// then
		assertEquals(ERROR_MESSAGE_INVALID_WORD_COUNT, wordsFOund.size() , COUNT_MULTIPLE_WORDS_TO_PROCCES);
	}
}
