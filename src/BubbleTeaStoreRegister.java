import java.util.*;

public class BubbleTeaStoreRegister {

    ArrayList<BubbleTea> drinksInCart = new ArrayList<>();
    static Scanner s = new Scanner(System.in);


    public static int getUserChoice(int min, int max, String question){

        System.out.println();
        int opt = 0;

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


    public static boolean yesOrNo(String question){

        String[] availableOptions = {"yes", "no", "y", "n"};
        String opt;


        while (true){
            System.out.println(question);
            opt = s.nextLine().toLowerCase();

            if (Arrays.asList(availableOptions).contains(opt)){
                break;
            }else{
                System.out.println("Enter a valid value. ");
            } // End if
        } // End while

        if (opt.equals("yes") || opt.equals("y")) {
            return true;
        }else{
            return false;
        } // End if
    }



    public void viewCart(){
        if (drinksInCart.isEmpty()){
            System.out.println("Your Cart is Empty. ");
        }else{
            for (int i = 0; i < drinksInCart.size(); i++){
                System.out.println(i + ". " + drinksInCart.get(i).toString());
            } // End for
        } // End if

    } // void viewCart()


    public static int showMenu(){
        int selection;

        System.out.println("1. Order Drinks");
        System.out.println("2. Exit");


        selection = getUserChoice(1,2, "Enter your Choice: ");
        return selection;
    }


    public static void makeSale(){




    }


    /**
     * Assemble a drink with a drink base, drink topping(s), sugar level, and ice level.
     * @param store The store to read the available contents from
     * @return A complete drink
     */
    public static BubbleTea createDrink(BubbleTeaStore store){

        int choice;
        boolean addToppings;
        ArrayList<String> tempList = new ArrayList<>();
        BubbleTea myDrink = new BubbleTea();

        System.out.println();



        // All drinks start with the base, so ask what drink they would like
        System.out.println("Choose a drink base: ");
        for (Map.Entry<String, Double> entry: store.getDrinkBases().entrySet()){

            System.out.println(entry.getKey() + " - $" + String.format("%.2f", entry.getValue()));
            tempList.add(entry.getKey());
        } // End for

        // Get the user's choice
        choice = getUserChoice(0, store.getDrinkBases().size() - 1, "Choose your base: ");

        // Debug lines
        System.out.println(choice);
        for (int i = 0; i < tempList.size(); i++){
            System.out.println(i + ". " + tempList.get(i));
        }
        System.out.println(store.getDrinkBases().containsKey(tempList.get(choice)));
        System.out.println(tempList.get(choice));
        System.out.println(store.getDrinkBases().get(tempList.get(choice)));

        // End of debug lines


        // Add the drink base and drink cost to the drink
        myDrink.setDrinkBase(tempList.get(choice));
        myDrink.setDrinkCost(myDrink.getDrinkCost() + store.getDrinkBases().get(tempList.get(choice)));

        tempList.clear();

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
                    System.out.println(entry.getKey() + " - $" + String.format("%.2f", entry.getValue()));
                    tempList.add(entry.getKey());
                } // End for

                choice = getUserChoice(0, store.getDrinkToppings().size() - 1, "Choose your topping: ");

                // Add the drink topping and topping cost to the drink
//                myDrink.addTopping(tempList.get(choice));
                myDrink.getToppings().add(tempList.get(choice));
                myDrink.setDrinkCost(myDrink.getDrinkCost() + store.getDrinkToppings().get(tempList.get(choice)));

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




        return myDrink;
    }







    public static void main(String[] args){

        // Create the store
        BubbleTeaStore store = BubbleTeaStore.createStore();

        int choice;

        while (true){
            System.out.println("Welcome to " + store.getStoreName());
            choice = showMenu();

            if (choice == 1){
                createDrink(store);
            }else{
             break;
            }

        }
    }
}
