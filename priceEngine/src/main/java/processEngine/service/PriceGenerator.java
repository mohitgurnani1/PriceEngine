package processEngine.service;

import processEngine.domain.PriceQuotation;
import processEngine.domain.Product;

import java.util.List;

/**
 * Created by Mohit on 11/29/2016.
 */
public interface PriceGenerator {
    public double generatePriceForProduct(List<PriceQuotation> priceQuotations, String productName);

}
