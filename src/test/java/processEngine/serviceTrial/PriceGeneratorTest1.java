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
public class PriceGeneratorTest1 {

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


}