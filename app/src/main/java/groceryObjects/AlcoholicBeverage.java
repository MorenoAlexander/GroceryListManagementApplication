package groceryObjects;

public class AlcoholicBeverage extends Beverage {

    private double aloholByVolume;
    AlcoholicBeverage(String Name, double Cost, double Volume,double AlcoholByVolume,int Quantity) {
        super(Name,Cost,Volume,Quantity);
        aloholByVolume = AlcoholByVolume;
    }


    public double getAloholByVolume() {
        return aloholByVolume;
    }

    public void setAloholByVolume(double aloholByVolume) {
        this.aloholByVolume = aloholByVolume;
    }
}
