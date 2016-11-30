package processEngine.input;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import processEngine.domain.PriceQuotation;
import processEngine.domain.ProductList;
import processEngine.service.InputParser;
import processEngine.service.impl.InputParserImpl;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by Mohit on 11/29/2016.
 */
public class InputTest {
    private static InputParser inputParser;
    @BeforeClass
    public static void setup(){
        inputParser = new InputParserImpl();
    }
    @Test(expectedExceptions = RuntimeException.class)
    public void whenInputHasInvalidNumberOfProducts(){
        String input = "a\nProduct1 H H";
        inputParser.parseInput(input);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void whenInputHasInvalidNumberOfProductInformation(){
        String input = "1\nProduct1 H H S";
        inputParser.parseInput(input);
    }


    @Test(expectedExceptions = RuntimeException.class,expectedExceptionsMessageRegExp = "Inconsistent Information: Provide Demand and Supply in 'H' or 'L' value")
    public void whenInputHasInvalidProductInformation(){
        String input = "1\nProduct1 H S";
        inputParser.parseInput(input);
        System.out.println();
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void whenInputHasInvalidNumberOfVendorEntries(){
        String input = "1\nProduct1 H L\na";
        inputParser.parseInput(input);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void whenInputHasInvalidNumberOfVendorEntriesInformation(){
        String input = "1\nProduct1 H L\n1\nflashdrive X 1.0 2";
        inputParser.parseInput(input);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void whenInputHasInvalidVendorEntriesInformation(){
        String input = "1\nProduct1 H L\n1\nflashdrive X abc";
        inputParser.parseInput(input);
    }

    @Test
    public void whenInputHas1ProductAnd1VendorEntry(){
        String input = "1\nflashdrive H L\n1\nflashdrive X 1.0";
        List<PriceQuotation> priceQuotations =inputParser.parseInput(input);
        assertEquals(true, ProductList.getProducts().size()>0);
        assertEquals(true, priceQuotations.size()>0);
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


