package groceryObjects;

public abstract class Beverage extends GroceryItem {
    private double volume; //measured in Fl Oz



    /****
    return cost $/Fl Oz
     */
    @Override
    public double getCost(){
        return cost/volume;
    }

    @Override
    public double getTotalCost(){
        return (cost/volume) * quantity;
    }

}
