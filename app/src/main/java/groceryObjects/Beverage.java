package groceryObjects;

public class Beverage extends GroceryItem {
    private double volume; //measured in Fl Oz


    public Beverage(String Name, double Cost, double Volume,int Quantity){
        name = Name;
        cost = Cost;
        volume = Volume;
        weight = Volume / 15.34; //@TODO replace placeholder
        quantity = Quantity;


    }
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
