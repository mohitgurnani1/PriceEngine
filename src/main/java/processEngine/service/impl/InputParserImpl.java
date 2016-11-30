package processEngine.service.impl;

import processEngine.domain.Product;
import processEngine.domain.SalesData;
import processEngine.service.InputParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class InputParserImpl implements InputParser {

    public List<Product> getProductsFromInput(String input) {

        List<Product> products = new ArrayList<Product>();
        Scanner scan = new Scanner(input);
        int numOfProducts = scan.nextInt();
        scan.nextLine();

        for (int i = 0; i < numOfProducts; i++) {
            String params[] = scan.nextLine().split(" ");
            Product product = parseProductFromInput(params, i + 1);
            products.add(product);
        }

        return products;
    }

    public List<SalesData> getSalesDataFromInput(String input) {

        List<SalesData> salesDataList = new ArrayList<SalesData>();
        Scanner scan = new Scanner(input);

        skipProductsFromParsing(scan);

        int numOfSalesData = scan.nextInt();
        scan.nextLine();

        for (int i = 0; i < numOfSalesData; i++) {
            String params[] = scan.nextLine().split(" ");
            SalesData salesData = parseSalesDataFromInput(params);
            salesDataList.add(salesData);

        }
        return salesDataList;
    }

    private Product parseProductFromInput(String params[], int productIndex) {
        if (params.length != 3)
            throw new RuntimeException("Incomplete/More than required Information about products");
        String productName = params[0];
        if (!((params[1].equals("H") || params[1].equals("L")) && (params[2].equals("H") || params[2].equals("L")))) //KISS
            throw new RuntimeException("Inconsistent Information: Provide Demand and Supply in 'H' or 'L' value");
        char demand = params[1].charAt(0);
        char supply = params[2].charAt(0);
        return new Product(productIndex, productName, demand, supply);
    }

    private SalesData parseSalesDataFromInput(String[] params) {

        if (params.length != 3)
            throw new RuntimeException("Incomplete/More than required Information about Sales Data");

        String productName = params[0];
        String vendorName = params[1];
        double price = Double.parseDouble(params[2]);
        return new SalesData(productName, vendorName, price);
    }

    private void skipProductsFromParsing(Scanner scan) {
        int numOfProducts = scan.nextInt();
        scan.nextLine();

        for (int i = 0; i < numOfProducts; i++)
            scan.nextLine();
    }
}
