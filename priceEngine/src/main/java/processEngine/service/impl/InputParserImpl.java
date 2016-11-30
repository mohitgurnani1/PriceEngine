package processEngine.service.impl;

import processEngine.domain.PriceQuotation;
import processEngine.domain.ProductList;
import processEngine.service.InputParser;

import java.util.*;

public class InputParserImpl implements InputParser {

    public List<PriceQuotation> parseInput(String input) {
        List<PriceQuotation> priceQuotations = null;
        Scanner scan=new Scanner(input);
        parseInputProducts(scan);
        priceQuotations = parseInputEntries(scan);
        return priceQuotations;
        }

    private void parseInputProducts(Scanner scan) {
        int numOfProducts=scan.nextInt();
        scan.nextLine();

        for(int i=0;i<numOfProducts;i++)
        {
            String params[]=scan.nextLine().split(" ");
            if(params.length!=3)
                throw new RuntimeException("Incomplete/More than required Information about products");
            String productName=params[0];
            if(!((params[1].equals("H") || params[1].equals("L")) && (params[2].equals("H") || params[2].equals("L")))) //KISS
                throw new RuntimeException("Inconsistent Information: Provide Demand and Supply in 'H' or 'L' value");
            char demand=params[1].charAt(0);
            char supply=params[2].charAt(0);
            ProductList.addProduct(i+1,productName,demand,supply);
        }
    }

    private List<PriceQuotation> parseInputEntries(Scanner scan) {
        List<PriceQuotation> priceQuotations = new ArrayList<PriceQuotation>();
        int numOfEntries=scan.nextInt();
        scan.nextLine();
        for(int i=0;i<numOfEntries;i++)
        {
            String params[]=scan.nextLine().split(" ");
            if(params.length!=3)
                throw new RuntimeException("Incomplete/More Information about Vendors Entry");

            String productName=params[0];
            String vendorName=params[1];
            double price=Double.parseDouble(params[2]);

            priceQuotations.add(new PriceQuotation(productName,vendorName,price));

        }
        return priceQuotations;
    }

}
