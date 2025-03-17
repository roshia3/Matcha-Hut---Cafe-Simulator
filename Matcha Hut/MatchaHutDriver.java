/* Driver class for Matcha hut 
 * checks for new customer
 * allows  user to order multiple drinks.
 * @Roshia_Dutta @version_1
 */

import java.util.Scanner;

public class MatchaHutDriver {

    //Main method to input drink choice, time, 
    //customer name, and pickup method
    public static void main(String[] args) {

        // Create a scanner for user input
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Matcha Hut! Here is our brand new menu\n");
        // Display the menu
        MatchaHut.displayMenu();

        OrderDetails order = null;

        // Collect order details

        //Array of 24 hours in a day 
        String[] timeArray = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", 
                "12", "13", "14", "15", "16", "17", "18", "19", "20", 
                "21", "22", "23", "24"};

        String orderTime;
        boolean isValid = false;

        //loop to input pickup time till input is valid
        do {
            System.out.print("\nWhen do you want your drink? (e.g., 7:00 AM): ");
            orderTime = scanner.nextLine().trim(); // Remove leading/trailing spaces

            if (orderTime.isEmpty()) {
                System.out.println("\nSorry, please enter a valid time. We are open 24/7.");
                continue; // Skip the rest and prompt again
            }

            // Extract first character (for single-digit numbers)
            char firstChar = orderTime.charAt(0);

            // If the first two characters form a valid number (e.g., "10", "12", "23"), extract them
            String extractedTime;
            if (orderTime.length() > 1 && Character.isDigit(orderTime.charAt(1))) {
                extractedTime = orderTime.substring(0, 2); // Take first two characters (ex: "12")
            } else {
                extractedTime = String.valueOf(firstChar); // Take first character (ex: "7")
            }

            //for-each loop to check if the extracted time is valid
            for (String time : timeArray) {
                if (time.equals(extractedTime)) {
                    isValid = true;
                    break;
                }
            }

            if (!isValid) {
                System.out.println("Sorry, please enter a valid time. We are open 24/7.");
            }

        } while (!isValid);

        System.out.println("\n" + orderTime + " is a great time for one of our refreshing drinks");

        System.out.print("Can I get a name for the order? ");
        String customerName = scanner.nextLine();
        System.out.println("Hello " + customerName + "!");

        String[] pickupOptions = {"Self-Pickup", "Drive-Through", "DoorDash"};
        String pickupMethod;
        boolean isValidP = false; //to validate 

        //loop to input pickup method till input is valid
        do {
            System.out.print("How would you like to pick up your order (Self-Pickup/Drive-Through/DoorDash): ");
            pickupMethod = scanner.nextLine().trim(); // Remove extra spaces

            for (String option : pickupOptions) {
                if (option.equalsIgnoreCase(pickupMethod)) {
                    isValidP = true;
                    break;
                }
            }

            if (!isValidP) {
                System.out.println("\nSorry, Please choose from: Self-Pickup, Drive-Through, or DoorDash.");
            }

        } while (!isValidP);

        // Delivery charge update
        if (pickupMethod.equalsIgnoreCase("DoorDash")) {
            System.out.println("\nThere will be a small $4 fee for DoorDash!");
        } else {
            System.out.println("\nYou will get your order by " + pickupMethod);
        }

        boolean orderMore = false; //checks for multiple orders from same customer
        boolean newCustomer = true; //checks for new customer
        System.out.println("\n\nMay I take your Order?");

        //do-while loop for multiple orders
        do{
            newCustomer = false; //allow multiple orders for the same customer
            System.out.print("Which drink would you like to order. You can choose from 1-12: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Collect size input
            String size = "";
            while (!(size.equalsIgnoreCase("small") || size.equalsIgnoreCase("large") || size.equalsIgnoreCase("medium"))){
                System.out.print("\nWhat size would you like your drink to be? We have Small, Medium,and Large: ");
                size = scanner.nextLine();
                if (!(size.equalsIgnoreCase("small") || size.equalsIgnoreCase("large") || size.equalsIgnoreCase("medium"))){
                    System.out.println("\nSorry, can you repeat that?");
                }
            }

            //switch-case to allow user to choose drink
            switch (choice) {
                case 1: // Espresso
                    System.out.println("Great choice! Our espresso is one of the best");
                    System.out.print("\nHow do you like your espresso? We can do short shots or long shots: ");
                    String espressoType = scanner.nextLine();
                    // Default customization
                    boolean espressoHot = false;
                    boolean espressoNoFoam = false;

                    // While loop till correct extra hot input
                    while (true) {
                        System.out.print("\nWould you like it extra hot? (yes/no): ");
                        String espresHot = scanner.nextLine().toLowerCase(); // Convert to lowercase
                        if (espresHot.equals("yes") || espresHot.equals("no")) {
                            espressoHot = espresHot.equals("yes"); // Set to true if "yes"
                            break; // Exit the loop after valid input
                        } else {
                            System.out.println("Sorry, please say yes or no");
                        }
                    }

                    // While loop till correct foam input
                    while (true) {
                        System.out.print("\nWould you like foam? (yes/no): ");
                        String foamInput = scanner.nextLine().toLowerCase(); // Convert to lowercase
                        if (foamInput.equals("yes") || foamInput.equals("no")) {
                            espressoNoFoam = foamInput.equals("yes"); // Set to true if "yes"
                            break; // Exit the loop after valid input
                        } else {
                            System.out.println("Sorry, please say yes or no");
                        }
                    }

                    Coffee espresso = new Coffee(); //create espresso object
                    espresso.Coffee("Espresso", size, 1, espressoHot, espressoNoFoam); //Coffee Constructor
                    order = new OrderDetails(orderTime, customerName, pickupMethod, espresso);
                    break;

                case 2: // Latte
                    System.out.println("Great choice! Our baristas do the best latte art");
                    System.out.print("\nHow many espresso shots would you like? (1 or 2): ");
                    int latteShots = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    // While loop till correct extra hot input
                    boolean latteHot = false;
                    while (true) {
                        System.out.print("\nWould you like it extra hot? (yes/no): ");
                        String latteHotInput = scanner.nextLine().toLowerCase(); // Convert to lowercase
                        if (latteHotInput.equals("yes") || latteHotInput.equals("no")) {
                            latteHot = latteHotInput.equals("yes"); // Set to true if "yes"
                            break; // Exit the loop after valid input
                        } else {
                            System.out.println("Sorry, please say yes or no");
                        }
                    }

                    // While loop till correct foam input
                    boolean latteNoFoam = false;
                    while (true) {
                        System.out.print("\nWould you like foam? (yes/no): ");
                        String latteNoFoamInput = scanner.nextLine().toLowerCase(); // Convert to lowercase
                        if (latteNoFoamInput.equals("yes") || latteNoFoamInput.equals("no")) {
                            latteNoFoam = latteNoFoamInput.equals("yes"); // Set to true if "yes"
                            break; // Exit the loop after valid input
                        } else {
                            System.out.println("Sorry, please say yes or no");
                        }
                    }

                    Coffee latte = new Coffee();
                    latte.Coffee("Latte", size, latteShots, latteHot, latteNoFoam);
                    order = new OrderDetails(orderTime, customerName, pickupMethod, latte);
                    break;

                case 3: // Cappuccino
                    System.out.println("Great choice! We have great cappuccino");
                    System.out.print("\nHow many espresso shots would you like? (1 or 2): ");
                    int cappuccinoShots = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    // While loop till correct extra hot input
                    boolean cappuccinoHot = false;
                    while (true) {
                        System.out.print("Would you like it extra hot? (yes/no): ");
                        String cappuccinoHotInput = scanner.nextLine().toLowerCase(); // Convert to lowercase
                        if (cappuccinoHotInput.equals("yes") || cappuccinoHotInput.equals("no")) {
                            cappuccinoHot = cappuccinoHotInput.equals("yes"); // Set to true if "yes"
                            break; // Exit the loop after valid input
                        } else {
                            System.out.println("Sorry, please say yes or no");
                        }
                    }

                    // While loop till correct foam input
                    boolean cappuccinoNoFoam = false;
                    while (true) {
                        System.out.print("Would you like foam? (yes/no): ");
                        String cappuccinoNoFoamInput = scanner.nextLine().toLowerCase(); // Convert to lowercase
                        if (cappuccinoNoFoamInput.equals("yes") || cappuccinoNoFoamInput.equals("no")) {
                            cappuccinoNoFoam = cappuccinoNoFoamInput.equals("yes"); // Set to true if "yes"
                            break; // Exit the loop after valid input
                        } else {
                            System.out.println("Sorry, please say yes or no");
                        }
                    }

                    Coffee cappuccino = new Coffee();
                    cappuccino.Coffee("Cappuccino", size, cappuccinoShots, cappuccinoHot, cappuccinoNoFoam);
                    order = new OrderDetails(orderTime, customerName, pickupMethod, cappuccino);
                    break;

                case 4: // Americano
                    System.out.println("Great choice! Our Americano is really energizing");
                    System.out.print("\nHow do you like your espresso? We can do short shots or long shots:  ");
                    String americanoType = scanner.nextLine();

                    // While loop till correct extra hot input
                    boolean americanoHot = false;
                    while (true) {
                        System.out.print("Would you like it extra hot? (yes/no): ");
                        String americanoHotInput = scanner.nextLine().toLowerCase(); // Convert to lowercase
                        if (americanoHotInput.equals("yes") || americanoHotInput.equals("no")) {
                            americanoHot = americanoHotInput.equals("yes"); // Set to true if "yes"
                            break; // Exit the loop after valid input
                        } else {
                            System.out.println("Sorry, please say yes or no");
                        }
                    }

                    Coffee americano = new Coffee();
                    americano.Coffee("Americano", size, 1, americanoHot, false);
                    order = new OrderDetails(orderTime, customerName, pickupMethod, americano);
                    break;

                case 5: // Green Tea
                    System.out.println("Great choice! Our calming teas are really good for you");

                    // While loop till correct lemon input
                    boolean TeaLemon = false;
                    while (true) {
                        System.out.print("\nWould you like lemon with your tea? (yes/no): ");
                        String TeaLemonInput = scanner.nextLine().toLowerCase(); // Convert to lowercase
                        if (TeaLemonInput.equals("yes") || TeaLemonInput.equals("no")) {
                            TeaLemon = TeaLemonInput.equals("yes"); // Set to true if "yes"
                            break; // Exit the loop after valid input
                        } else {
                            System.out.println("Sorry, please say yes or no");
                        }
                    }

                    // While loop till correct sugar input
                    boolean TeaSugar = false;
                    while (true) {
                        System.out.print("Do you want sugar? (yes/no): ");
                        String blackTeaSugarInput = scanner.nextLine().toLowerCase(); // Convert to lowercase
                        if (blackTeaSugarInput.equals("yes") || blackTeaSugarInput.equals("no")) {
                            TeaSugar = blackTeaSugarInput.equals("yes"); // Set to true if "yes"
                            break; // Exit the loop after valid input
                        } else {
                            System.out.println("Sorry, please say yes or no");
                        }
                    }

                    Tea greenTea = new Tea(size, "Green Tea", TeaLemon, false, TeaSugar); //create green tea object
                    order = new OrderDetails(orderTime, customerName, pickupMethod, greenTea);
                    break;

                case 6: // Black Tea
                    System.out.println("Great choice! Our calming teas are really good for you");

                    // While loop till correct lemon input
                    TeaLemon = false;
                    while (true) {
                        System.out.print("\nWould you like lemon with your tea? (yes/no): ");
                        String TeaLemonInput = scanner.nextLine().toLowerCase(); // Convert to lowercase
                        if (TeaLemonInput.equals("yes") || TeaLemonInput.equals("no")) {
                            TeaLemon = TeaLemonInput.equals("yes"); // Set to true if "yes"
                            break; // Exit the loop after valid input
                        } else {
                            System.out.println("Sorry, please say yes or no");
                        }
                    }

                    // While loop till correct half-and-half milk input
                    boolean blackTeaHalfHalf = false;
                    while (true) {
                        System.out.print("Do you want half-and-half milk? (yes/no): ");
                        String blackTeaHalfHalfInput = scanner.nextLine().toLowerCase(); // Convert to lowercase
                        if (blackTeaHalfHalfInput.equals("yes") || blackTeaHalfHalfInput.equals("no")) {
                            blackTeaHalfHalf = blackTeaHalfHalfInput.equals("yes"); // Set to true if "yes"
                            break; // Exit the loop after valid input
                        } else {
                            System.out.println("Sorry, please say yes or no");
                        }
                    }

                    // While loop till correct sugar input
                    TeaSugar = false;
                    while (true) {
                        System.out.print("Do you want sugar? (yes/no): ");
                        String TeaSugarInput = scanner.nextLine().toLowerCase(); // Convert to lowercase
                        if (TeaSugarInput.equals("yes") || TeaSugarInput.equals("no")) {
                            TeaSugar = TeaSugarInput.equals("yes"); // Set to true if "yes"
                            break; // Exit the loop after valid input
                        } else {
                            System.out.println("Sorry, please say yes or no");
                        }
                    }

                    Tea blackTea = new Tea(size, "Black Tea", TeaLemon, blackTeaHalfHalf, TeaSugar);
                    order = new OrderDetails(orderTime, customerName, pickupMethod, blackTea);
                    break;

                case 7: // Chai Latte
                    System.out.println("Great choice! Our baristas do the best latte art");

                    // While loop till correct spicy input
                    boolean chaiSpice = false;
                    while (true) {
                        System.out.print("\nWould you like your chai spicy? (yes/no): ");
                        String chaiSpiceInput = scanner.nextLine().toLowerCase(); // Convert to lowercase
                        if (chaiSpiceInput.equals("yes") || chaiSpiceInput.equals("no")) {
                            chaiSpice = chaiSpiceInput.equals("yes"); // Set to true if "yes"
                            break; // Exit the loop after valid input
                        } else {
                            System.out.println("Sorry, please say yes or no");
                        }
                    }

                    // While loop till correct extra hot input
                    boolean chaiHot = false;
                    while (true) {
                        System.out.print("Do you want it extra hot? (yes/no): ");
                        String chaiHotInput = scanner.nextLine().toLowerCase(); // Convert to lowercase
                        if (chaiHotInput.equals("yes") || chaiHotInput.equals("no")) {
                            chaiHot = chaiHotInput.equals("yes"); // Set to true if "yes"
                            break; // Exit the loop after valid input
                        } else {
                            System.out.println("Sorry, please say yes or no");
                        }
                    }

                    Tea chaiLatte = new Tea(size, "Chai Latte", chaiSpice, chaiHot, false);
                    order = new OrderDetails(orderTime, customerName, pickupMethod, chaiLatte);
                    break;

                case 8: // Hot Chocolate
                    System.out.println("Great choice! Hot chocolate is best for cold nights like this");

                    // While loop till correct whipped cream input
                    boolean hotChocoWhip = false;
                    while (true) {
                        System.out.print("\nWould you like Whipped Cream? (yes/no): ");
                        String hotChocoWhipInput = scanner.nextLine().toLowerCase(); // Convert to lowercase
                        if (hotChocoWhipInput.equals("yes") || hotChocoWhipInput.equals("no")) {
                            hotChocoWhip = hotChocoWhipInput.equals("yes"); // Set to true if "yes"
                            break; // Exit the loop after valid input
                        } else {
                            System.out.println("Sorry, please say yes or no");
                        }
                    }

                    // While loop till correct marshmallows input
                    boolean hotChocoMarshmellow = false;
                    while (true) {
                        System.out.print("Do you want marshmallows in your hot chocolate? (yes/no): ");
                        String hotChocoMarshmellowInput = scanner.nextLine().toLowerCase(); // Convert to lowercase
                        if (hotChocoMarshmellowInput.equals("yes") || hotChocoMarshmellowInput.equals("no")) {
                            hotChocoMarshmellow = hotChocoMarshmellowInput.equals("yes"); // Set to true if "yes"
                            break; // Exit the loop after valid input
                        } else {
                            System.out.println("Sorry, please say yes or no");
                        }
                    }

                    OtherBeverages hotChocolate = new OtherBeverages(size, "Hot Chocolate", false, false,
                            false, false, false, false, hotChocoWhip, hotChocoMarshmellow);
                    order = new OrderDetails(orderTime, customerName, pickupMethod, hotChocolate);
                    break;

                case 9: // Matcha Latte
                    System.out.println("Great choice! Our baristas do the best latte art");

                    // While loop till correct extra sweet input
                    boolean matchaSweet = false;
                    while (true) {
                        System.out.print("\nDo you want your Matcha extra sweet? (yes/no): ");
                        String matchaSweetInput = scanner.nextLine().toLowerCase(); // Convert to lowercase
                        if (matchaSweetInput.equals("yes") || matchaSweetInput.equals("no")) {
                            matchaSweet = matchaSweetInput.equals("yes"); // Set to true if "yes"
                            break; // Exit the loop after valid input
                        } else {
                            System.out.println("Sorry, please say yes or no");
                        }
                    }

                    // While loop till correct hot or cold input
                    boolean matchaHot = false;
                    while (true) {
                        System.out.print("Do you want your Matcha hot or cold? (hot/cold): ");
                        String matchaHotInput = scanner.nextLine().toLowerCase(); // Convert to lowercase
                        if (matchaHotInput.equals("hot") || matchaHotInput.equals("cold")) {
                            matchaHot = matchaHotInput.equals("hot"); // Set to true if "hot"
                            break; // Exit the loop after valid input
                        } else {
                            System.out.println("Sorry, please say hot or cold");
                        }
                    }

                    OtherBeverages matchaLatte = new OtherBeverages(size, "Matcha Latte",
                            false, false, false, false, matchaHot, matchaSweet, false, false);
                    order = new OrderDetails(orderTime, customerName, pickupMethod, matchaLatte);
                    break;

                case 10: // Iced Coffee
                    System.out.println("Great choice! Our iced coffee is really refreshing");

                    // While loop till correct extra ice input
                    boolean icedCoffeeIce = false;
                    while (true) {
                        System.out.print("\nWould you like extra ice? (yes/no): ");
                        String icedCoffeeIceInput = scanner.nextLine().toLowerCase(); // Convert to lowercase
                        if (icedCoffeeIceInput.equals("yes") || icedCoffeeIceInput.equals("no")) {
                            icedCoffeeIce = icedCoffeeIceInput.equals("yes"); // Set to true if "yes"
                            break; // Exit the loop after valid input
                        } else {
                            System.out.println("Sorry, please say yes or no");
                        }
                    }

                    // While loop till correct sweet input
                    boolean icedCoffeeSweet = false;
                    while (true) {
                        System.out.print("Do you want your iced coffee sweet? (yes/no): ");
                        String icedCoffeeSweetInput = scanner.nextLine().toLowerCase(); // Convert to lowercase
                        if (icedCoffeeSweetInput.equals("yes") || icedCoffeeSweetInput.equals("no")) {
                            icedCoffeeSweet = icedCoffeeSweetInput.equals("yes"); // Set to true if "yes"
                            break; // Exit the loop after valid input
                        } else {
                            System.out.println("Sorry, please say yes or no");
                        }
                    }

                    OtherBeverages icedCoffee = new OtherBeverages(size, "Iced Coffee",
                            icedCoffeeSweet, icedCoffeeIce, false, false, false, false, false, false);
                    order = new OrderDetails(orderTime, customerName, pickupMethod, icedCoffee);
                    break;

                case 11: // Strawberry Açai Juice
                    System.out.println("Great choice! Our Strawberry açai is really refreshing");

                    // While loop till correct extra sweet input
                    boolean extraSweet = false;
                    while (true) {
                        System.out.print("\nDo you want your strawberry açai extra sweet? (yes/no): ");
                        String extraSweetInput = scanner.nextLine().toLowerCase(); // Convert to lowercase
                        if (extraSweetInput.equals("yes") || extraSweetInput.equals("no")) {
                            extraSweet = extraSweetInput.equals("yes"); // Set to true if "yes"
                            break; // Exit the loop after valid input
                        } else {
                            System.out.println("Sorry, please say yes or no");
                        }
                    }

                    // While loop till correct iced input
                    boolean iced = false;
                    while (true) {
                        System.out.print("Do you want your drink iced? (yes/no): ");
                        String icedInput = scanner.nextLine().toLowerCase(); // Convert to lowercase
                        if (icedInput.equals("yes") || icedInput.equals("no")) {
                            iced = icedInput.equals("yes"); // Set to true if "yes"
                            break; // Exit the loop after valid input
                        } else {
                            System.out.println("Sorry, please say yes or no");
                        }
                    }

                    OtherBeverages strawberryAcai = new OtherBeverages(size, "Strawberry Açai Juice",
                            extraSweet, iced, false, false, false, false, false, false);
                    order = new OrderDetails(orderTime, customerName, pickupMethod, strawberryAcai);
                    break;

                case 12: // Orange Soda
                    System.out.println("Great choice! Our Orange soda is really refreshing");

                    // While loop till correct extra bubbly input
                    boolean extraBubbly = false;
                    while (true) {
                        System.out.print("\nDo you want your soda extra bubbly? (yes/no): ");
                        String extraBubblyInput = scanner.nextLine().toLowerCase(); // Convert to lowercase
                        if (extraBubblyInput.equals("yes") || extraBubblyInput.equals("no")) {
                            extraBubbly = extraBubblyInput.equals("yes"); // Set to true if "yes"
                            break; // Exit the loop after valid input
                        } else {
                            System.out.println("Sorry, please say yes or no");
                        }
                    }

                    // While loop till correct ice input
                    boolean withIce = false;
                    while (true) {
                        System.out.print("Would you like ice with your drink? (yes/no): ");
                        String withIceInput = scanner.nextLine().toLowerCase(); // Convert to lowercase
                        if (withIceInput.equals("yes") || withIceInput.equals("no")) {
                            withIce = withIceInput.equals("yes"); // Set to true if "yes"
                            break; // Exit the loop after valid input
                        } else {
                            System.out.println("Sorry, please say yes or no");
                        }
                    }

                    //create Other beverages object
                    OtherBeverages orangeSoda = new OtherBeverages(size, "Orange Soda",
                            false, false, extraBubbly, withIce, false, false, false, false); 
                    
                    //OrderDetails constructor
                    order = new OrderDetails(orderTime, customerName, pickupMethod, orangeSoda);
                    break;
                default:
                    System.out.println("Invalid choice. Please restart the order.");
                    break;
            }

            // Display and write order details if the order was created
            if (order != null) {

                order.writeOrderToFile(!orderMore); // Write to file (append if ordering more, overwrite if not)
            }

            System.out.println("Here is your order");
            DisplayOrder show = new DisplayOrder();
            show.displayOrderFromFile(); // Display the order

            System.out.println("Can I get you anything else? (yes/no)");
            String userOrder = scanner.nextLine();
            orderMore = Character.toLowerCase(userOrder.charAt(0)) == 'y';

        } while (orderMore);
        newCustomer = true; // Reset for the next customer
        if (!orderMore) {
            double totalCombinedPrice = OrderDetails.getTotalCombinedPrice(); // Get the total combined price
            order.displayFinalMessage(orderMore, totalCombinedPrice); // Display the final message
        }
        scanner.close(); // Close the scanner
    }
}
