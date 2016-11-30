package processEngine.service;

import processEngine.domain.Product;
import processEngine.domain.SalesData;

import java.util.List;

/**
 * Created by Mohit on 11/29/2016.
 */
public interface InputParser {


    public List<Product> getProductsFromInput(String input);
    public List<SalesData> getSalesDataFromInput(String input);

}
