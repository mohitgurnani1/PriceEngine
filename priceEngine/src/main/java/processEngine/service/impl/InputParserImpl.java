package processEngine.service.impl;

import processEngine.domain.Product;
import processEngine.domain.ProductList;
import processEngine.domain.Vendor;
import processEngine.service.InputParser;

import java.util.*;

/**
 * Created by Mohit on 11/29/2016.
 */
public class InputParserImpl implements InputParser {

    public Set<Vendor> parseInput(String input) {
        Set<Vendor> vendors=null;
        Scanner scan=new Scanner(input);
        parseInputProducts(scan);
        vendors = parseInputEntries(scan);
        return vendors;
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

    private Set<Vendor> parseInputEntries(Scanner scan) {
        int numOfEntries=scan.nextInt();
        scan.nextLine();
        HashMap<String,Vendor> vendorHashMap=new HashMap<String, Vendor>();

        for(int i=0;i<numOfEntries;i++)
        {
            String params[]=scan.nextLine().split(" ");
            if(params.length!=3)
                throw new RuntimeException("Incomplete Information about Vendors");

            String productName=params[0];
            String vendorName=params[1];
            double price=Double.parseDouble(params[2]);

            if(!(vendorHashMap.containsValue(vendorName))){
                Vendor vendor=new Vendor(i+1,vendorName);
                vendor.addProduct(ProductList.getProduct(productName),price);
                vendorHashMap.put(vendorName,vendor);
            }
            else{
                Vendor vendor=vendorHashMap.get(vendorName);
                vendor.addProduct(ProductList.getProduct(productName),price);
                //vendorHashMap.put(vendorName,vendor);   Testing reqd
            }

        }
        return (Set)vendorHashMap.values();

    }

}
