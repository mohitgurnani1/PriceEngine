package processEngine.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohit on 11/29/2016.
 */
public class ProductList {
    private static List<Product> products=new ArrayList<Product>();
    public static void addProduct(int productId,String productName, char demand, char supply ){
        products.add(new Product(productId,productName,demand,supply));
    }

    public static List<Product> getProducts(){
        return products;
    }

    public static Product getProduct(String productName){

        for(Product product:products)
            if(product.getName().equals(productName))
                return product;

        throw new RuntimeException("ProductNotFoundException: product with given product Name: "+productName+" does not exist");
    }

}
