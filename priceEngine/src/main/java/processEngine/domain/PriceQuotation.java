package processEngine.domain;

/**
 * Created by Saurabh on 11/30/2016.
 */
public class PriceQuotation {
    String productName;
    String vendorName;
    double price;

    public PriceQuotation(String productName, String vendorName, double price) {
        this.productName = productName;
        this.vendorName = vendorName;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public double getPrice() {
        return price;
    }
}
