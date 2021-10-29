package utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import dto.SearchResult;

public class StatsEngine {

	
	private final static int MAX_RESULSET = 10;
	public void calculateStats(List<SearchResult> resultData){
		boolean dataFound = false;
		if(!resultData.isEmpty()){
			for(SearchResult elem: resultData){
				if(elem.getElementsFound() >0){
					dataFound = true;
					BigDecimal acceptanceRatio = new BigDecimal(elem.getElementsFound()).divide(new BigDecimal(elem.getWordLenght()), 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
					elem.setAcceptanceRate(acceptanceRatio.round(MathContext.DECIMAL32).intValue());
				}else{
					elem.setAcceptanceRate(0);
				}
			}	
		}
		if(!dataFound){
			resultData.removeAll(resultData);
			resultData = new ArrayList<SearchResult>();
		}else{
			
			resultData.sort(Comparator.comparingDouble(SearchResult::getAcceptanceRate).reversed());
			if(resultData.size()>MAX_RESULSET){
				for(int i =0 ;i<resultData.size(); i++)
					resultData.remove(MAX_RESULSET);	
			}
		}
	}
}
