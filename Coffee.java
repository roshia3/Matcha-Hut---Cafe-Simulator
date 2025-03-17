/* Class for Coffee beverages
 * Allows different customization for all drinks
 * 
 * @Roshia_Dutta @version_1
 */

import java.io.FileWriter;
import java.io.IOException;

public class Coffee extends MatchaHut {
    private String type;
    private String size;
    private int espressoShots;
    private boolean extraHot;
    private boolean noFoam;
    private double price;

   //No-argument Constructor
   public Coffee() {
        /* Calling the constructor of the parent class Beverage to set type to "Coffee" 
         * and initialize a default size.
         */
        super("Coffee", "Medium"); // type and default size
    }

   /* Constructor
     * @param: String orderTime, customerName,  pickupMethod
     * @param: object beverage from MatchaHut class
     * initializes parameters
     */
    public void Coffee(String type, String size, int espressoShots, boolean extraHot, boolean noFoam) {
        this.type = type;
        this.size = size;
        this.espressoShots = espressoShots;
        this.extraHot = extraHot;
        this.noFoam = noFoam;
        setPrice(); // Update price based on type and size
    }

    // Getter for type
    public String getType() {
        return type;
    }

    // Setter for type
    public void setType(String type) {
        this.type = type;
        setPrice(); // Recalculate price when type changes
    }

    // Getter for size
    public String getSize() {
        return size;
    }

    // Setter for size
    public void setSize(String size) {
        this.size = size;
        setPrice(); // Recalculate price when size changes
    }

    // Getter for espresso shots
    public int getEspressoShots() {
        return espressoShots;
    }

    // Setter for espresso shots
    public void setEspressoShots(int espressoShots) {
        this.espressoShots = espressoShots;
    }

    // Getter for extra hot
    public boolean isExtraHot() {
        return extraHot;
    }

    // Setter for extra hot
    public void setExtraHot(boolean extraHot) {
        this.extraHot = extraHot;
    }

    // Getter for no foam
    public boolean isNoFoam() {
        return noFoam;
    }

    // Setter for no foam
    public void setNoFoam(boolean noFoam) {
        this.noFoam = noFoam;
    }

    // Set the price based on type and size
    private void setPrice() {
        double basePrice = 0.0;
        double sizeMultiplier = 1.0;

        // Set base price based on type
        switch (type.toLowerCase()) {
            case "espresso":
                basePrice = 2.50;
                break;
            case "latte":
                basePrice = 3.50;
                break;
            case "cappuccino":
                basePrice = 3.75;
                break;
            case "americano":
                basePrice = 2.75;
                break;
            default:
                basePrice = 0.0;
                break;
        }

        // Adjust price based on size
        switch (size.toLowerCase()) {
            case "small":
                sizeMultiplier = 1.0; // No change
                break;
            case "medium":
                sizeMultiplier = 1.2; // 20% increase
                break;
            case "large":
                sizeMultiplier = 1.5; // 50% increase
                break;
            default:
                sizeMultiplier = 1.0; // Default to small
                break;
        }

        this.price = basePrice * sizeMultiplier; // Set the price
    }

    // Getter for price
    public double getPrice() {
        return price;
    }

    // Getter for order details
    public String getOrderDetails() {
        //Order details according to customizations
        String orderDetails = "Coffee Order: " + size + " " + type +
                                "\n- Espresso Shots: " + espressoShots +
                                "\n- Temperature: " + (extraHot ? "Extra Hot" : "Regular") +
                                "\n- Foam: " + (noFoam ? "No Foam" : "With Foam") +
                                 String.format("\nPrice: $%.2f", price);

        return orderDetails;
    }

    // Display coffee details
    public void displayOrder() {
        String orderDetails = getOrderDetails();

        // Write to Order file
        try (FileWriter writer = new FileWriter("order details.txt", true)) {
            writer.write(orderDetails);
            writer.close();
        } 
        catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}