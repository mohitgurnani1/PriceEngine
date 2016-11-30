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
public class PriceGeneratorTest2 {

    private PriceGenerator priceGenerator;
    private InputParser inputParser;

    @BeforeClass
    public void setup() {
        priceGenerator = new PriceGeneratorImpl();
        inputParser = new InputParserImpl();
    }


    @Test
    public void whenOneProductAndManySalesDataIsGivenWithMultiplePriceMultipleFrequencies() {
        String input = "1\nProduct1 H H\n6\nProduct1 X 9.0\nProduct1 Y 8.0\nProduct1 Z 9.0\nProduct2 Z 9.0\nProduct1 Z 8.0\nProduct1 Z 7.0";
        List<Product> productsFromInput = inputParser.getProductsFromInput(input);
        List<SalesData> salesDataFromInput = inputParser.getSalesDataFromInput(input);
        assertEquals(priceGenerator.generatePriceForProduct(productsFromInput.get(0), salesDataFromInput), 8.0);
    }

    @Test
    public void whenOneProductAndManySalesDataIsGivenWithNoFrequency() {
        String input = "1\nProduct1 H H\n3\nProduct1 X 9.0\nProduct1 Y 8.0\nProduct1 Z 7.0";
        List<Product> productsFromInput = inputParser.getProductsFromInput(input);
        List<SalesData> salesDataFromInput = inputParser.getSalesDataFromInput(input);
        assertEquals(priceGenerator.generatePriceForProduct(productsFromInput.get(0), salesDataFromInput), 7.0);
    }


    @Test
    public void whenOneProductAndManySalesDataIsGivenWithMultiplePriceMultipleFrequenciesWithHighDemandHighSupply() {
        String input = "2\nflashdrive H H\nssd H H\n5\nflashdrive X 1.0\nssd X 10.0\nflashdrive Y 0.9\nflashdrive Z 1.1\nssd Y 12.5";
        List<Product> productsFromInput = inputParser.getProductsFromInput(input);
        List<SalesData> salesDataFromInput = inputParser.getSalesDataFromInput(input);
        assertEquals(priceGenerator.generatePriceForProduct(productsFromInput.get(0), salesDataFromInput), 0.9);
    }

    @Test
    public void whenOneProductAndManySalesDataIsGivenWithMultiplePriceMultipleFrequenciesWithHighDemandLowSupply() {
        String input = "1\nflashdrive H L\n3\nflashdrive X 10.0\nflashdrive Y 12.0\nflashdrive Z 10.0";
        List<Product> productsFromInput = inputParser.getProductsFromInput(input);
        List<SalesData> salesDataFromInput = inputParser.getSalesDataFromInput(input);
        assertEquals(priceGenerator.generatePriceForProduct(productsFromInput.get(0), salesDataFromInput), 10.5);
    }

}