package processEngine.serviceTrial;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import processEngine.domain.Product;
import processEngine.domain.SalesData;
import processEngine.service.InputParser;
import processEngine.service.PriceGenerator;
import processEngine.service.impl.InputParserImpl;
import processEngine.service.impl.PriceGeneratorImpl;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by Mohit on 11/30/2016.
 */
public class PriceGeneratorTest3 {

    private PriceGenerator priceGenerator;
    private InputParser inputParser;

    @BeforeClass
    public void setup() {
        priceGenerator = new PriceGeneratorImpl();
        inputParser = new InputParserImpl();
    }

    @Test
    public void whenOneProductAndManySalesDataIsGivenWithMultiplePriceMultipleFrequenciesWithLowDemandHighSupply() {
        String input = "1\nflashdrive L H\n3\nflashdrive X 10.0\nflashdrive Y 12.0\nflashdrive Z 10.0";
        List<Product> productsFromInput = inputParser.getProductsFromInput(input);
        List<SalesData> salesDataFromInput = inputParser.getSalesDataFromInput(input);
        assertEquals(priceGenerator.generatePriceForProduct(productsFromInput.get(0), salesDataFromInput), 9.5);
    }

    @Test
    public void whenOneProductAndManySalesDataIsGivenWithMultiplePriceMultipleFrequenciesWithLowDemandLowSupply() {
        String input = "1\nflashdrive L L\n3\nflashdrive X 10.0\nflashdrive Y 12.0\nflashdrive Z 10.0";
        List<Product> productsFromInput = inputParser.getProductsFromInput(input);
        List<SalesData> salesDataFromInput = inputParser.getSalesDataFromInput(input);
        assertEquals(priceGenerator.generatePriceForProduct(productsFromInput.get(0), salesDataFromInput), 11.0);
    }


    @Test
    public void whenOneProductAndManySalesDataIsGivenWithMultiplePriceMultipleFrequenciesWithDemandAndSupply() {
        String input = "2\nflashdrive H H\nssd H L\n5\nflashdrive X 1.0\nssd X 10.0\nflashdrive Y 0.9\nflashdrive Z 1.1\nssd Y 12.5";
        List<Product> productsFromInput = inputParser.getProductsFromInput(input);
        List<SalesData> salesDataFromInput = inputParser.getSalesDataFromInput(input);
        ArrayList expected = new ArrayList<Double>();
        expected.add(0.9);
        expected.add(10.5);
        assertEquals(priceGenerator.generatePriceForAllProducts(productsFromInput, salesDataFromInput), expected);
    }
}