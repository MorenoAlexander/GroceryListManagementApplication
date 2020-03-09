package groceryObjects;

public abstract class Meat extends GroceryItem {

    @Override
    public double getCost(){
        return cost/weight;
    }
}
