import java.io.*;
import java.util.*;

public class BubbleTeaStore implements Serializable{

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

    /**
     * Create a BubbleTeaStore object text file in the directory.
     * @param store The store to save
     * @param filename The .txt file to save it to
     * @return If it was successful (true) or not (false)
     * @throws IOException
     */
    public static boolean writeStoreToFile(BubbleTeaStore store, String filename) throws IOException {

        try{

            // Copy the contents of the store to a file
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(store);

            // Close the file
            out.close();

            // If there is an IOException, return false
        } catch (IOException e){
            return false;
        } // End try
        // Otherwise return true
        return true;


    } // static boolean writeStoreToFile(BubbleTeaStore, String)


    /**
     * Load a store a file.
     * @param filename The name of the file to load
     * @return The store
     */
    public static BubbleTeaStore loadStoreFile(String filename){
        try{

            // Open the file, read in the object and parse it as a BubbleTeaStore object
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));

            BubbleTeaStore store = (BubbleTeaStore) in.readObject();


            // Close the file and return the store
            in.close();
            return store;

        } catch (ClassNotFoundException | IOException e) {
            return null;
        } // End try
    }

    /**
     * Check if a file exists in directory.
     * @param filename the name of the file to search for
     * @return Whether it exists (true) or not (false)
     */
    public static boolean storeExists(String filename){
        File fileToCheck = new File(filename);
        boolean exists = fileToCheck.exists();
        return exists;
    } // static boolean storeExists(String)


    /**
     * View all Products in a store (for administrative purposes).
     * @param store Store to view the contents of
     */
    public static void listStore(BubbleTeaStore store){

        int i = 1;

        System.out.println("---|| All Drink Bases and Prices ||---");
        // List all the Drink bases and Prices
        for (Map.Entry<String, Double> entry : store.getDrinkBases().entrySet()){
            System.out.println(i + ". " + entry.getKey() + " - $" + String.format("%.2f", entry.getValue()));
            i++;
        } // End for

        i=1;
        System.out.println();
        System.out.println();
        System.out.println("---|| All Drink Toppings and Prices ||---");
        for (Map.Entry<String, Double> entry : store.getDrinkToppings().entrySet()){
            System.out.println(i + ". " + entry.getKey() + " - $" + String.format("%.2f", entry.getValue()));
            i++;
        } // End for
        System.out.println();
        System.out.println();
        System.out.println("---|| All Ice levels ||---");
        for (int j = 0; j < store.getIceLevels().size(); j++){
            System.out.println((j+1) + ". " + store.getIceLevels().get(j));
        }// End for

        System.out.println();
        System.out.println();
        System.out.println("---|| All Sugar levels ||---");
        for (int j = 0; j < store.getSugarLevels().size(); j++){
            System.out.println((j+1) + ". " + store.getSugarLevels().get(j));
        }// End for
        System.out.println();
        System.out.println();


    }


}
