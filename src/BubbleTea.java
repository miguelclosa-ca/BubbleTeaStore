import java.util.ArrayList;

public class BubbleTea {


    private String drinkBase, iceLevel, sugarLevel;

    private ArrayList<String> toppings;

    private double drinkCost;

    public BubbleTea(){
        toppings = new ArrayList<>();

    }

    public BubbleTea(String initDrinkBase, String initIceLevel, String initSugarLevel, ArrayList<String> initToppings, double initDrinkCost){
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

    public ArrayList<String> getToppings() {
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

    public void setToppings(ArrayList<String> toppings) {
        this.toppings = toppings;
    }

    public void setDrinkCost(double drinkCost) {
        this.drinkCost = drinkCost;
    }


//    public void addTopping(String topping){
//        toppings.add(topping);
//    }

    @Override
    public String toString() {


        // Create the string to return
        String s = "";

        s += "===" + "\n";
        if (!getToppings().isEmpty()){
            s += getDrinkBase() + " with toppings:" + "\n";
            for (int i = 0; i < getToppings().size(); i++){
                s += i + ". " + getToppings().get(i) + "\n";
            } // End for
            s += "with " + getIceLevel() + " and " + getSugarLevel() + " at a cost of $" + String.format("%.2f", getDrinkCost()) + "\n";
        }else{
            s += getDrinkBase() + " with no toppings, with " + getIceLevel() + " and " + getSugarLevel() + " at a cost of $" + String.format("%.2f", getDrinkCost()) + "\n";
        } // End if
        s += "===";
        return s;
    }
}
