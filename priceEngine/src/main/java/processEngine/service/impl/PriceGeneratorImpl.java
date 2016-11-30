package processEngine.service.impl;

import processEngine.domain.PriceQuotation;
import processEngine.domain.Product;
import processEngine.service.PriceGenerator;

import java.util.List;

public class PriceGeneratorImpl implements PriceGenerator{

    public double chooseMinimum(List<PriceQuotation> priceQuotations, String productName) {
        for (PriceQuotation priceQuotation: priceQuotations) {
            if(priceQuotation.getProductName().equals(productName)){

            }
        }
        return 0;
    }
    public double generatePriceForProduct(List<PriceQuotation> priceQuotations, String productName) {
        double minimumPrice = chooseMinimum(priceQuotations,productName);
        return 0;
    }
}
