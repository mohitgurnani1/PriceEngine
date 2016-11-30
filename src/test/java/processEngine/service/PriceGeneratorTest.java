package processEngine.service;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import processEngine.domain.Product;
import processEngine.domain.SalesData;
import processEngine.service.impl.InputParserImpl;
import processEngine.service.impl.PriceGeneratorImpl;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by Mohit on 11/30/2016.
 */
public class PriceGeneratorTest {

    private PriceGenerator priceGenerator;
    private InputParser inputParser;

    @BeforeClass
    public void setup() {
        priceGenerator = new PriceGeneratorImpl();
        inputParser = new InputParserImpl();
    }

    @Test
    public void whenOneProductAndOneSalesDataIsGiven() {
        String input = "1\nProduct1 H H\n1\nProduct1 X 9.0";
        List<Product> productsFromInput = inputParser.getProductsFromInput(input);
        List<SalesData> salesDataFromInput = inputParser.getSalesDataFromInput(input);
        assertEquals(priceGenerator.generatePriceForProduct(productsFromInput.get(0), salesDataFromInput), 9.0);
    }

    @Test
    public void whenOneProductAndManySalesDataIsGiven() {
        String input = "1\nProduct1 H H\n3\nProduct1 X 9.0\nProduct1 Y 10.0\nProduct1 Z 6.0";
        List<Product> productsFromInput = inputParser.getProductsFromInput(input);
        List<SalesData> salesDataFromInput = inputParser.getSalesDataFromInput(input);
        assertEquals(priceGenerator.generatePriceForProduct(productsFromInput.get(0), salesDataFromInput), 6.0);
    }


    @Test
    public void whenOneProductAndManySalesDataIsGivenWithMultipleFrequencies() {
        String input = "1\nProduct1 H H\n3\nProduct1 X 9.0\nProduct1 Y 8.0\nProduct1 Z 9.0";
        List<Product> productsFromInput = inputParser.getProductsFromInput(input);
        List<SalesData> salesDataFromInput = inputParser.getSalesDataFromInput(input);
        assertEquals(priceGenerator.generatePriceForProduct(productsFromInput.get(0), salesDataFromInput), 9.0);
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