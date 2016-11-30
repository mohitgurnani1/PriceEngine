package processEngine.service;

import processEngine.domain.Product;
import processEngine.domain.SalesData;

import java.util.List;

/**
 * Created by Mohit on 11/29/2016.
 */
public interface PriceGenerator {

    public double generatePriceForProduct(Product product, List<SalesData> salesDataList);

    public List<Double> generatePriceForAllProducts(List<Product> products, List<SalesData> salesDataList);

}
