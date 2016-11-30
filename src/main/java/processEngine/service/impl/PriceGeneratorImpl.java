package processEngine.service.impl;

import com.sun.deploy.model.DownloadDelegate;
import com.sun.deploy.resources.Deployment_pt_BR;
import processEngine.domain.Product;
import processEngine.domain.SalesData;
import processEngine.service.PriceGenerator;

import java.util.*;

public class PriceGeneratorImpl implements PriceGenerator {

    public List<Double> generatePriceForAllProducts(List<Product> products, List<SalesData> salesDataList) {
        List<Double> priceList = new ArrayList<Double>();
        for (Product product : products)
            priceList.add(generatePriceForProduct(product, salesDataList));
        return priceList;
    }


    public double generatePriceForProduct(Product product, List<SalesData> salesDataList) {

        Map<Double, Integer> salesFrequencyMap = getSalesDataForSpecificProductAlongWithFrequency(product, salesDataList);
        double average = getAverageSalesOfProduct(salesFrequencyMap);
        List<Double> noiseData = getNoiseData(salesFrequencyMap, average);
        eliminateNoiseData(salesFrequencyMap, noiseData);
        double minimumSale = getMinimumSaleWithMultipleFrequency(salesFrequencyMap);
        double sellingPrice = getPriceAsPerDemandAndSupply(minimumSale, product.getDemand(), product.getSupply());
        return sellingPrice;
    }

    private double getPriceAsPerDemandAndSupply(double minimumSale, char demand, char supply) {
        double sellingPrice = 0;
        if (demand == 'H' && supply == 'H')
            sellingPrice = minimumSale;
        else if (demand == 'H' && supply == 'L')
            sellingPrice = 1.05 * minimumSale;
        else if (demand == 'L' && supply == 'H')
            sellingPrice = 0.95 * minimumSale;
        else
            sellingPrice = 1.1 * minimumSale;
        return sellingPrice;
    }

    private double getMinimumSaleWithMultipleFrequency(Map<Double, Integer> salesFrequencyMap) {
        double minimumSale = 0;
        for (Map.Entry<Double, Integer> entry : salesFrequencyMap.entrySet()) {
            if (minimumSale == 0)
                minimumSale = entry.getKey();
            if (entry.getValue() > 1) {
                minimumSale = entry.getKey();
                break;
            }
        }
        return minimumSale;
    }

    private void eliminateNoiseData(Map<Double, Integer> salesFrequencyMap, List<Double> noiseData) {
        for (double noise : noiseData)
            salesFrequencyMap.remove(noise);
    }

    private List<Double> getNoiseData(Map<Double, Integer> salesFrequencyMap, double average) {
        List<Double> removeValues = new ArrayList<Double>();
        for (Double sale : salesFrequencyMap.keySet())
            if (sale <= 0.5 * average || sale >= 1.5 * average)
                removeValues.add(sale);
        return removeValues;
    }

    private double getAverageSalesOfProduct(Map<Double, Integer> salesFrequencyMap) {
        double average = 0;

        for (Double sale : salesFrequencyMap.keySet())
            average += sale;

        average /= salesFrequencyMap.size();
        return average;
    }

    private Map getSalesDataForSpecificProductAlongWithFrequency(Product product, List<SalesData> salesDataList) {

        Map<Double, Integer> salesFrequencyMap = new TreeMap<Double, Integer>();
        for (SalesData salesData : salesDataList) {

            if (product.getName().equals(salesData.getProductName())) {

                double price = salesData.getPrice();
                if (!salesFrequencyMap.containsKey(price))
                    salesFrequencyMap.put(price, 1);
                else {
                    int freq = salesFrequencyMap.get(price);
                    salesFrequencyMap.put(price, freq + 1);
                }
            }
        }
        return salesFrequencyMap;
    }


}
