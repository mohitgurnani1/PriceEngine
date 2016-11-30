package processEngine.service;

import processEngine.domain.PriceQuotation;

import java.util.List;

/**
 * Created by Mohit on 11/29/2016.
 */
public interface InputParser {



    public List<PriceQuotation> parseInput(String input);

}
