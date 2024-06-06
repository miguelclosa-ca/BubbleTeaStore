/**
 *
 */

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.time.*;

public class BubbleTeaStoreRegister {

    static Scanner s = new Scanner(System.in);
//    static String exchangeRate = "1,$";
    static LocalDate currDate = LocalDate.now();
    static LocalTime currTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);


    /**
     * Get the user's choice given a numbered list of objects.
     * @param min The minimum number to choose from
     * @param max The maximum number to choose from
     * @param question The question to ask the user
     * @return The user's valid selection
     */
    public static int getUserChoice(int min, int max, String question){

        System.out.println();
        int opt = 0;

        // Attempt to ask for a valid value
        do{
            try{
                System.out.print(question);
                opt = s.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Invalid input");
            }

            if (opt < min || opt > max){
                System.out.println(opt + " is out of range. ");

            } // End if
            s.nextLine();
        } while (opt < min || opt > max);
        return opt;
    } // int getUserChoice(int, int, String)


    /**
     * Ask a simple yes/no question. Get the user's response after asking.
     * @param question The question to ask
     * @return A boolean expression mimicking the user saying yes (true) or no (false)
     */
    public static boolean yesOrNo(String question){

        // Create an array of available options
        String[] availableOptions = {"yes", "no", "y", "n"};
        String opt;


        while (true){
            // Ask the question
            System.out.println(question);

            // Get the user's input and turn the input into lowercase
            opt = s.nextLine().toLowerCase();

            // If the input is valid, escape this while loop
            if (Arrays.asList(availableOptions).contains(opt)){
                break;
            }else{
                // Otherwise do not
                System.out.println("Enter a valid value. ");
            } // End if
        } // End while

        // If the user says yes, return true
        if (opt.equals("yes") || opt.equals("y")) {
            return true;
        }else{

            // Otherwise, return false
            return false;
        } // End if
    } // boolean yesOrNo(String)



    public static void viewCart(ArrayList<BubbleTea> drinksInCart, String exchangeRate){
        if (drinksInCart.isEmpty()){
            System.out.println("Your Cart is Empty. ");
        }else{
            for (int i = 0; i < drinksInCart.size(); i++){
                System.out.println("Drink " + (i+1) + ". " + "\n" +drinksInCart.get(i).printReceiptFormat(exchangeRate));
                System.out.println();
            } // End for
        } // End if

    } // void viewCart()

    public static void printMenu(String[] options){
        for (int i = 0; i < options.length; i++){
            System.out.println((i+1) + ". " + options[i]);
        } // End for

    } // void printMenu(String[])



    public static int showMenu(){
        int selection;
        String[] menu = {"Order Drink(s)", "Administrative Options", "Exit"};
        printMenu(menu);

        selection = getUserChoice(1,3, "Enter your Choice: ");
        return selection;
    } // int showMenu()


    public static ArrayList<BubbleTea> makeSale(BubbleTeaStore store){

        boolean additionalDrinks;

        // To make a sale, we'll need to keep track of the cart
        ArrayList<BubbleTea> currentCart = new ArrayList<>();



        // Make one drink at first
        currentCart.add(createDrink(store));


        // Ask if more drinks should be made
        additionalDrinks = yesOrNo("Create Drink " + (currentCart.size() + 1) + "?");
        System.out.println();

        // If yes, add as much drinks as needed.
        if (additionalDrinks){
            while (true){
                currentCart.add(createDrink(store));
                additionalDrinks = yesOrNo("Create Drink " + (currentCart.size() + 1) + "?");
                if (!additionalDrinks){
                    break;
                } // End if
            } // End while
        } // End if

        return currentCart;


    } // ArrayList<BubbleTea> makeSale(BubbleTeaStore)

    public static void printReceipt(ArrayList<BubbleTea> cart, BubbleTeaStore store){

        double subtotal = 0;
        double tax = 0;
        double total = 0;
        String[] exchange = store.getExchangeRate().split(",");


        // If not, calculate the subtotal, tax rate, and total
        for (int i = 0; i < cart.size(); i++){
            subtotal += cart.get(i).getDrinkCost() * Double.parseDouble(exchange[0]);
        } // End for

        tax = (subtotal * store.getTaxRate());
        total = (subtotal + tax);

        System.out.println();
        System.out.println();

        System.out.println(store.getStoreName());
        System.out.println(store.getStoreLocation());
        System.out.println("Sale made on " + currDate + " at " + currTime);
        System.out.println();

        System.out.println("Drinks: --- ");
        System.out.println("=-=-=-=");
        viewCart(cart, store.getExchangeRate());
        System.out.println("=-=-=-=");

        System.out.println();
        System.out.println();

        System.out.println("Subtotal: "+ exchange[1] + String.format("%.2f", subtotal) );
        System.out.println("Tax: "+ exchange[1] + String.format("%.2f", tax));
        System.out.println("Total: "+ exchange[1] + String.format("%.2f", total));

        System.out.println();
    }


    /**
     * Assemble a drink with a drink base, drink topping(s), sugar level, and ice level.
     * @param store The store to read the available contents from
     * @return A complete drink
     */
    public static BubbleTea createDrink(BubbleTeaStore store){

        int choice;
        int index = 1;
        boolean addToppings;
        ArrayList<String> tempList = new ArrayList<>();
        BubbleTea myDrink = new BubbleTea();
//        String[] exchange = exchangeRate.split(",");

        System.out.println();



        // All drinks start with the base, so ask what drink they would like
        System.out.println("Choose a drink base: ");
        for (Map.Entry<String, Double> entry: store.getDrinkBases().entrySet()){

            System.out.println(index + ". " + entry.getKey() + " - $" + String.format("%.2f", entry.getValue()));

            // If we directly add the exchange rate into the price of each item right now
//            System.out.println(index + ". " + entry.getKey() + " - " + exchange[1] + String.format("%.2f", (entry.getValue() * Integer.valueOf(exchange[0]))));
            tempList.add(entry.getKey());
            index++;
        } // End for

        // Get the user's choice
        choice = getUserChoice(0, store.getDrinkBases().size(), "Choose your base: ") - 1;



        // Add the drink base and drink cost to the drink
        myDrink.setDrinkBase(tempList.get(choice));
        myDrink.setDrinkCost(myDrink.getDrinkCost() + store.getDrinkBases().get(tempList.get(choice)));

        // Keep track of how much the drink base costs as well
        myDrink.setBaseCost(store.getDrinkBases().get(tempList.get(choice)));

        tempList.clear();
        index = 1;

        // Ask if toppings would like to be added
        System.out.println();
        addToppings = yesOrNo("Add toppings? [Yes or No]");


        // If yes, then add toppings until not required.
        if (addToppings){

            boolean addMoreToppings;

            // This follows the same logic as choosing a drink base, however the user can add as much
            // drink toppings as they would like.

            while (true){

                System.out.println("Choose a topping: ");
                for (Map.Entry<String, Double> entry : store.getDrinkToppings().entrySet()){
                    System.out.println(index + ". " + entry.getKey() + " - $" + String.format("%.2f", entry.getValue()));
                    tempList.add(entry.getKey());
                    index++;
                } // End for

                choice = getUserChoice(0, store.getDrinkToppings().size(), "Choose your topping: ") - 1;

                // Add the drink topping and topping cost to the drink
                myDrink.getToppings().add(tempList.get(choice));
                myDrink.setDrinkCost(myDrink.getDrinkCost() + store.getDrinkToppings().get(tempList.get(choice)));

                // Keep track of the cost of the toppings too
                myDrink.getToppingCosts().add(store.getDrinkToppings().get(tempList.get(choice)));

                tempList.clear();
                index = 0;

                // Ask the user if they would like to add another topping
                addMoreToppings = yesOrNo("Add more toppings? ");

                if (!addMoreToppings){
                    break;
                } // End if

            } // End while
        }else{
            System.out.println("No toppings to be added. ");
            System.out.println();
        } // End if

        // After adding toppings (or not), add ice levels:

        System.out.println();


        // Show all ice levels
        for (int i = 0; i < store.getIceLevels().size(); i++){
            System.out.println((i+1) + ". " + store.getIceLevels().get(i));
        } // End for

        // Ask the user what ice level they would like
        // then add the ice level to the drink
        choice = getUserChoice(1, store.getIceLevels().size(), "Choose your Ice Level: ") - 1;
        myDrink.setIceLevel(store.getIceLevels().get(choice));

        // After adding the ice, add the sugar

        for (int i = 0; i < store.getSugarLevels().size(); i++){
            System.out.println((i+1) + ". " + store.getSugarLevels().get(i));
        } // End for
        choice = getUserChoice(0, store.getSugarLevels().size(), "Choose your Sugar Level: ") - 1;
        myDrink.setSugarLevel(store.getSugarLevels().get(choice));

        // Now the drink is complete.
        return myDrink;
    } // BubbleTea createDrink(BubbleTeaStore)

    /**
     * Change parameters of the BubbleTeaStore.
     * @param store The store to modify
     */
    public static void administrativeMode(BubbleTeaStore store){
        String[] adminMenu = {"Edit Exchange Rate", "Edit Tax Rate", "Add/Remove item", "Exit"};
        int opt;

        while (true){

            System.out.println("---|| Admin Menu ||---");
            printMenu(adminMenu);

            opt = getUserChoice(1, 4, "Enter your choice: ");

            if (opt == 1){
                // Change the exchange rate
                store.setExchangeRate(changeExchangeRate(store.getExchangeRate()));
            }else if (opt == 2){
                // Change the tax rate
                store.setTaxRate(changeTaxRate(store.getTaxRate()));
            }else if (opt == 3){
                // Add or remove an item
                modifyAttributesMenu(store);
            }else{
                // Exit administrative options
                break;
            } // End if


        } // End while


    } // void administrativeMode()

    public static String changeExchangeRate(String currentExRate){
        String[] rates = {"1,$", "114,円", "995.97,₩", "0.73,$", "0.67€", "0.57,£", "2.73,﷼"};
        String[] rateNames = {"Canadian Dollar", "Japanese Yen", "South Korean Won", "US Dollar", "Euro", "British Pounds", "Saudi Riyals"};
        int opt;

        System.out.println();
        System.out.println("The currency in use right now is: " + rateNames[Arrays.asList(rates).indexOf(currentExRate)]);
        System.out.println();
        printMenu(rateNames);
        opt = getUserChoice(1, 7, "Which currency to change to?: ") - 1;

        if (rates[opt].equals(currentExRate)){
            System.out.println("Exchange rate will not be changed!");
        }else{
            currentExRate = rates[opt];
            System.out.println("Exchange rate changed to " + rateNames[Arrays.asList(rates).indexOf(currentExRate)] + ".");
        } // End if

        System.out.println();
        return currentExRate;


    }

    /**
     * Change the tax rate of the store.
     * @param currentRate The current tax rate the store will change
     * @return The tax rate the user would like to change it to as a double
     */
    public static double changeTaxRate(double currentRate){
        double opt;

        System.out.println();
        System.out.println("The tax rate is currently: " + String.format("%.2f", (currentRate * 100)) + "%.");
        System.out.println();

        System.out.print("Enter a tax rate to change to: ");
        opt = (s.nextDouble()) / 100;

        currentRate = opt;

        System.out.println("The tax rate is now: " + String.format("%.2f", (currentRate * 100)) + "%.");

        return currentRate;

    }


    public static BubbleTeaStore loadStore(){
        int opt;
        String filename;
        BubbleTeaStore store = null;
        String[] initialOptions = {"Read from file", "Load example store"};
        // Load a store saved on the computer or open an example store

        // Ask the user for their choice
        System.out.println("---|| Choose Load Type: ||---");

        printMenu(initialOptions);
        opt = getUserChoice(1, 2, "Select an option: ");


        // If they choose to open a file,
        if (opt == 1){
            // Specify what file to load
            while (true){
                System.out.print("Enter the filename: ");
                filename = s.nextLine();

                // If the file exists, load the store from that file
                if (BubbleTeaStore.storeExists(filename)){
                    store = BubbleTeaStore.loadStoreFile(filename);
                    break;

                    // Otherwise, do not load the file and ask for another file
                }else{
                    System.out.println(filename + " does not exist. Enter a different name.");
                } // End if
            } // End while


            // Otherwise, create a store from the BubbleTeaStore.createStore() method
        }else{
            // Create an example store
            store = BubbleTeaStore.createStore();
        } // End if

        // Return the store
        return store;
    } // static BubbleTeaStore loadStore()

    public static void modifyAttributesMenu(BubbleTeaStore store){
        int opt;
        String[] modMenu = {"View Whole Store", "Add Item", "Remove Item", "Exit"};

        while (true){
            System.out.println();
            System.out.println("---|| Add / Remove Menu ||---");
            printMenu(modMenu);
            opt = getUserChoice(1,4, "Enter your choice: " ) ;
            if (opt == 1){
                // View all

                BubbleTeaStore.listStore(store);

            }else if (opt == 2){
                addItem(store);
                // add item
            }else if (opt == 3){
                // remove item
            } else{
                break;
            } // End if
        } // End while


    } // void modifyAttributesMenu


    /**
     * In this menu-driven method, add either drink bases, drink toppings, ice levels, or sugar levels.
     * @param store The store to add to
     */
    public static void addItem(BubbleTeaStore store){
        int opt;
        String input;
        double price;
        String[] addMenu = {"Add Drink Base", "Add Topping", "Add Ice Level", "Add Sugar Level", "Exit"};

        while (true){
            System.out.println();
            System.out.println("---|| Add Menu ||---");
            printMenu(addMenu);
            opt = getUserChoice(1,5,"Enter your choice: ");

            if (opt == 1){

                // If the user selects 1, ask for the name of the drink base and the price of the drink
                System.out.println("Adding a Drink Base.");
                System.out.println();
                System.out.print("Enter the name of the drink base: " );
                input = s.nextLine();

                System.out.println();
                System.out.println("How much will " + input + " cost? ");
                price = s.nextDouble();

                // Add the drink base and cost to the HashMap of drink bases.
                System.out.println("Adding " + input + " at a cost of $" + String.format("%.2f", price));
                store.getDrinkBases().put(input, price);

            } else if (opt == 2) {

                // If 2, ask for the name of the drink topping and the price of the topping
                System.out.println("Adding a Drink Topping.");
                System.out.println();
                System.out.print("Enter the name of the drink topping: " );
                input = s.nextLine();

                System.out.println();
                System.out.println("How much will " + input + " cost? ");
                price = s.nextDouble();

                // Add the drink base and cost to the HashMap of drink toppings.
                System.out.println("Adding " + input + " at a cost of $" + String.format("%.2f", price));
                store.getDrinkToppings().put(input, price);
            } else if (opt == 3){

                // If 3, ask for the percentage level of ice (between 0-150%)
                System.out.println("Adding an Ice Level.");
                System.out.println();

                // This can be done by utilizing the getUserChoice() method written earlier to get a valid input.
                opt = getUserChoice(0, 150, "Enter the Percentage of Ice to add: ");

                // Add the ice level to the ArrayList of ice levels.
                System.out.println("Adding " + opt + "% Ice");
                store.getIceLevels().add(opt + "% Ice");


            } else if (opt == 4){

                // If 4, ask for the name of the level of sugar
                System.out.println("Adding a Sugar Level.");
                System.out.println();

                System.out.print("Enter A Sugar Level: ");
                input = s.nextLine();

                // Add the sugar level to the ArrayList of sugar levels.
                System.out.println("Adding Sugar Level: " + input);
                store.getSugarLevels().add(input);
            }else{
                break;
            } // End if
        } // End while

    } // void addItem(BubbleTeaStore)


    public static void main(String[] args) throws IOException {

        int choice;
        boolean opt;

        // Create a store - it can either be loaded in from file or an example store will be made.
        BubbleTeaStore store = loadStore();


        while (true){
            System.out.println("Welcome to " + store.getStoreName());
            choice = showMenu();

            if (choice == 1){
                printReceipt(makeSale(store), store);
            }else if (choice == 2){
                administrativeMode(store);
            }else{

                // Ask to save the store to a file or not
                opt = yesOrNo("Save store to file? ");

                // If so, define a filename and write the store to file
                if (opt == true){
                    System.out.print("Enter the file name: ");
                    String name = s.nextLine();

                    // Check if the file exists already
                    if (BubbleTeaStore.storeExists(name)){
                        // If so, append "_copy" to the end to keep from overwriting
                        System.out.println(name + " already exists in the directory. ");
                        name += "_copy";

                    } // End if
                    System.out.println("Saving file as: " + name + ".");
                    store.writeStoreToFile(store, name);
                } // End if

                // Then quit the program regardless if the user specified yes or no
                break;
            } // End if

        } // End while
    }
}
