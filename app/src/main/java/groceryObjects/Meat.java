package groceryObjects;

import java.io.Serializable;

public class Meat extends GroceryItem implements Serializable {

    public Meat(String Name, double Cost, double Weight, int Quantity){
        name = Name;
        cost = Cost;
        weight = Weight;
        quantity = Quantity;
    }
    @Override
    public double getCost(){
        return cost/weight;
    }


    @Override
    public double getTotalCost(){
        return quantity * getCost();
    }
}
