import java.util.*;

public class BubbleTeaStore {

    private String storeName, storeLocation;
    private String exchangeRate;


    private double taxRate;



    // Since different drinks have different prices, it would make sense to store drink bases in a HashMap
    // So that we can both store the drink name and the price
    // Same goes with toppings.
    private HashMap<String, Double> drinkBases;
    private HashMap<String, Double> drinkToppings;

    private ArrayList<String> iceLevels;
    private ArrayList<String> sugarLevels;




    public BubbleTeaStore(String initName, String initLocation){
        storeName = initName;
        storeLocation = initLocation;
        drinkBases = new HashMap<>();
        drinkToppings = new HashMap<>();
        iceLevels = new ArrayList<>();
        sugarLevels = new ArrayList<>();
        taxRate = 0.13;
        exchangeRate = "1,$";
    }

    /**
     * Getters and Setters
     */


    public String getStoreName() {
        return storeName;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public HashMap<String, Double> getDrinkBases() {
        return drinkBases;
    }

    public HashMap<String, Double> getDrinkToppings() {
        return drinkToppings;
    }

    public ArrayList<String> getIceLevels() {
        return iceLevels;
    }

    public ArrayList<String> getSugarLevels() {
        return sugarLevels;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public static BubbleTeaStore createStore(){

        // Create some examples of ice levels and sugar levels

        String[] exampleIceLevels = {"No Ice", "20% Ice", "40% Ice", "60% Ice", "80% Ice", "100% Ice"};
        String[] exampleSugarLevels = {"No Sugar", "Some Sugar", "Half Sugar", "More Sugar", "Maximum Sugar"};


        // Create some examples of drink bases and toppings along with their prices

        String[] exampleDrinkBases = {"Classic Milk Tea", "Taro Milk Tea", "Brown Sugar Milk Tea", "Wintermelon Milk Tea", "Mango Smoothie", "Lychee Smoothie", "Caramel Milk Tea"};
        Double[] exampleDrinkBasesPrices = {5.0, 5.2, 6.0, 6.2, 5.5, 5.5, 5.7};

        String[] exampleDrinkToppings = {"Tapioca", "Grass Jelly", "Popping Boba", "Coconut Jelly"};
        Double[] exampleDrinkToppingsPrices = {0.0, 0.5, 0.75, 0.5};



        // Create a store with a name

        BubbleTeaStore myStore = new BubbleTeaStore("My Cool Boba Store", "Toronto, ON");

        // Initialize the ice and sugar levels available in this store
        myStore.getIceLevels().addAll(List.of(exampleIceLevels));
        myStore.getSugarLevels().addAll(List.of(exampleSugarLevels));


        // Add all drink bases and toppings to the store

        for (int i = 0; i < exampleDrinkBases.length; i++){
            myStore.getDrinkBases().put(exampleDrinkBases[i], exampleDrinkBasesPrices[i]);
        } // End for

        for (int i = 0; i < exampleDrinkToppings.length; i++){
            myStore.getDrinkToppings().put(exampleDrinkToppings[i], exampleDrinkToppingsPrices[i]);
        } // End for


        // Return the store
        return myStore;

    } // static BubbleTeaStore createStore()


}
