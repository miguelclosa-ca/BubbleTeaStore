public class BubbleTea {


    private String drinkBase, iceLevel, sugarLevel;

    // A drink can have up to 3 toppings at once
    private String[] toppings = new String[3];

    private double drinkCost;

    public BubbleTea(){
    }

    public BubbleTea(String initDrinkBase, String initIceLevel, String initSugarLevel, String[] initToppings, double initDrinkCost){
        drinkBase = initDrinkBase;
        iceLevel = initIceLevel;
        sugarLevel = initSugarLevel;
        toppings = initToppings;
        drinkCost = initDrinkCost;
    }

    /**
     * Getters and Setters
     */

    public String getDrinkBase() {
        return drinkBase;
    }

    public String getIceLevel() {
        return iceLevel;
    }

    public String getSugarLevel() {
        return sugarLevel;
    }

    public String[] getToppings() {
        return toppings;
    }

    public double getDrinkCost() {
        return drinkCost;
    }

    public void setDrinkBase(String drinkBase) {
        this.drinkBase = drinkBase;
    }

    public void setIceLevel(String iceLevel) {
        this.iceLevel = iceLevel;
    }

    public void setSugarLevel(String sugarLevel) {
        this.sugarLevel = sugarLevel;
    }

    public void setToppings(String[] toppings) {
        this.toppings = toppings;
    }

    public void setDrinkCost(double drinkCost) {
        this.drinkCost = drinkCost;
    }


}
