package processEngine.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohit on 11/29/2016.
 */
public class Vendor {

    private int id;
    private String name;
    private List<Product> productList;

    public Vendor(){}

    public Vendor(int id, String name) {
        this.id = id;
        this.name = name;
        productList=new ArrayList<Product>();
    }

/*
    @Override
    public int hashCode(){
        return id;
    }
*/

    @Override
    public boolean equals(Object obj) {
        if(this.name.equals(((Vendor)obj).name))
            return true;
        else
            return false;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void addProduct(Product product,double price)
    {
        Product temporary=product.getClone();               //Deep Copying
        temporary.setPrice(price);
        this.productList.add(temporary);
    }
}
