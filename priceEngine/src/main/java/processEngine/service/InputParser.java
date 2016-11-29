package processEngine.service;

import processEngine.domain.Vendor;

import java.util.Set;

/**
 * Created by Mohit on 11/29/2016.
 */
public interface InputParser {



    public Set<Vendor> parseInput(String input);

}
