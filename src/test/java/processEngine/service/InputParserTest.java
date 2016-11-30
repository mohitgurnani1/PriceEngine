package processEngine.service;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import processEngine.service.InputParser;
import processEngine.service.impl.InputParserImpl;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by Mohit on 11/29/2016.
 */
public class InputParserTest {
    private static InputParser inputParser;
    @BeforeClass
    public static void setup(){
        inputParser = new InputParserImpl();
    }

    @Test(expectedExceptions = java.util.InputMismatchException.class)
    public void whenInputHasInvalidNumberOfProducts(){
        String input = "a\nProduct1 H H";
        inputParser.getProductsFromInput(input);
    }

    @Test(expectedExceptions = RuntimeException.class,expectedExceptionsMessageRegExp = "Incomplete/More than required Information about products")
    public void whenInputHasInvalidNumberOfProductInformation(){
        String input = "1\nProduct1 H H S";
        inputParser.getProductsFromInput(input);
    }


    @Test(expectedExceptions = RuntimeException.class,expectedExceptionsMessageRegExp = "Inconsistent Information: Provide Demand and Supply in 'H' or 'L' value")
    public void whenInputHasInvalidProductInformation(){
        String input = "1\nProduct1 H S";
        inputParser.getProductsFromInput(input);
    }

    @Test
    public void whenInputHasValidProductInformation(){
        String input = "1\nProduct1 H L";
        inputParser.getProductsFromInput(input);
        assert(true);
    }


    @Test(expectedExceptions = java.util.InputMismatchException.class)
    public void whenInputHasInvalidNumberOfSalesDataEntries(){
        String input = "1\nProduct1 H L\na";
        inputParser.getSalesDataFromInput(input);
    }


    @Test(expectedExceptions = RuntimeException.class,expectedExceptionsMessageRegExp = "Incomplete/More than required Information about Sales Data")
    public void whenInputHasInvalidNumberOfSalesDataEntriesInformation(){
        String input = "1\nProduct1 H L\n1\nflashdrive X 1.0 2";
        inputParser.getSalesDataFromInput(input);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void whenInputHasInvalidSalesDataEntriesInformation(){
        String input = "1\nProduct1 H L\n1\nflashdrive X abc";
        inputParser.getSalesDataFromInput(input);
    }

    @Test
    public void whenInputHas1ProductAnd1SalesDataEntry(){
        String input = "1\nflashdrive H L\n1\nflashdrive X 1.0";
        assertEquals(true, inputParser.getProductsFromInput(input).size()>0);
        assertEquals(true, inputParser.getSalesDataFromInput(input).size()>0);
    }



/*
    @Test
    public void whenInputHasManyProducts(){

        Product A=new Product("p1","flashDrive",'H','L');
        Product B=new Product("p2","penDrive",'L','H');

        Vendor v1=new Vendor("v1","X");
        v1.addProduct(A,10);
        v1.addProduct(B,22);

        Vendor v2=new Vendor("v2","Y");
        v2.addProduct(A,12);
        v2.addProduct(B,20);

        List<Vendor> vendors=new ArrayList<Vendor>();

        PriceGenerator priceGenerator=new PriceGeneratorImpl();
        Assert.assertEquals(true,priceGenerator.generatePriceForProduct(A)>0);

    }
*/
}


