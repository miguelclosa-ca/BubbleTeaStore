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






    public static void main(String[] args){

        // Create the store
        BubbleTeaStore store = BubbleTeaStore.createStore();

        int choice;

        while (true){
            System.out.println("Welcome to " + store.getStoreName());
            choice = showMenu();

            if (choice == 2){
                break;
            }

        }
    }
}
