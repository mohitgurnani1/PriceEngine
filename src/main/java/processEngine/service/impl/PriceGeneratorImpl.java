package processEngine.service.impl;

import processEngine.domain.Product;
import processEngine.domain.SalesData;
import processEngine.service.PriceGenerator;

import java.util.List;

public class PriceGeneratorImpl implements PriceGenerator{

    public double chooseMinimum(List<SalesData> priceQuotations, String productName) {
        for (SalesData priceQuotation: priceQuotations) {
            if(priceQuotation.getProductName().equals(productName)){

            }
        }
        return 0;
    }
    public double generatePriceForProduct(List<SalesData> salesData, String productName) {
        double minimumPrice = chooseMinimum(salesData,productName);
        return 0;
    }

    public double generatePriceForProduct(Product product, List<SalesData> salesDataList) {
        return 0;
    }

    public List<Double> generatePriceForAllProducts(List<Product> products, List<SalesData> salesDataList) {
        return null;
    }
}
