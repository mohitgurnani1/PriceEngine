package processEngine.domain;

public class Product implements Cloneable {

    private int id;
    private String name;   //doubt
    private char demand;
    private char supply;

    public Product() {
    }

    public Product(int id, String name, char demand, char supply) {
        this.id = id;
        this.name = name;
        this.demand = demand;
        this.supply = supply;
    }
/*
    public Product getClone()  {
        Product product=null;
        try {
            product=(Product) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return product;
    }*/

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public char getDemand() {
        return demand;
    }

    public void setDemand(char demand) {
        this.demand = demand;
    }

    public char getSupply() {
        return supply;
    }

    public void setSupply(char supply) {
        this.supply = supply;
    }

}
