package groceryObjects;

import java.io.Serializable;

public abstract class GroceryItem implements Serializable {


    public enum type {BEVERAGE,MEAT}
    protected String name;
    protected double cost;
    protected double weight; //Measured in Lbs //Idea. Use Spinner to allow user to enter measurement. We'll see...
    protected int quantity;





    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public abstract double getCost(); //Why Abstract? Beverages will handle costs differently than other items
    //For example I want beverages to represented in cents/Fl Oz, or meat as $/Lb

    public abstract double getTotalCost();
    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }
}
