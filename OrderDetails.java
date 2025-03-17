/*
 * class to define customer name, pickup time, etc 
 * Writes order to order details file
 *
 * @Roshia_Dutta @version_1
 */

import java.io.FileWriter;
import java.io.IOException;

public class OrderDetails {
    //private data members
    private String orderTime;
    private String customerName;
    private String pickupMethod;
    private MatchaHut beverage;
    private int deliveryCharge;
    private double totalPrice;

    // Static variables to track delivery charge and total combined price
    private static boolean isDeliveryCharged = false;
    private static double totalCombinedPrice = 0.0;

    /* Constructor
     * @param: String orderTime, customerName,  pickupMethod
     * @param: object beverage from MatchaHut class
     * initializes parameters
     */
    
    public OrderDetails(String orderTime, String customerName, String pickupMethod, MatchaHut beverage) {
        this.orderTime = orderTime;
        this.customerName = customerName;
        this.pickupMethod = pickupMethod;
        this.beverage = beverage;
        this.deliveryCharge = 0;
        this.totalPrice = 0.0;

        // Update delivery charge based on pickup method
        setDeliveryCharge(); // Call this method to update delivery charge
        setTotalPrice();
    }

    // Setter method for time
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    // Getter for time
    public String getOrderTime() {
        return orderTime;
    }

    // Setter methods for customer name
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    // Getter for customer name
    public String getCustomerName() {
        return customerName;
    }

    // Setter methods for pickup method
    public void setPickupMethod(String pickupMethod) {
        this.pickupMethod = pickupMethod;
        setDeliveryCharge(); // Update delivery charge whenever pickup method is set
    }

    // Setter for Delivery Charge
    public void setDeliveryCharge() {
        //Extra $4 charge for DoorDash Delivery
        if (pickupMethod.equalsIgnoreCase("DoorDash")) {
            this.deliveryCharge = 4;
        } else {
            this.deliveryCharge = 0; // Reset delivery charge if pickup method is not DoorDash
        }
    }

    // Getter for Delivery Charge
    public int getDeliveryCharge() {
        return deliveryCharge;
    }

    // Getter for pickup method
    public String getPickupMethod() {
        return pickupMethod;
    }

    
    /* // Setter method for total price
     * 
     * Preconditions:
     * - beverage must be an instance of Coffee, Tea, or OtherBeverages.
     * - Each beverage type must have a valid getPrice() method.
     * - deliveryCharge should be a valid numeric value (default or assigned).
     *
     * Postconditions:
     * - totalPrice is updated to include the beverage price and the delivery charge.
     */
    private void setTotalPrice() {
        //Calculate price based on beverage
        if (beverage instanceof Coffee) {
            this.totalPrice += ((Coffee) beverage).getPrice();
        } else if (beverage instanceof Tea) {
            this.totalPrice += ((Tea) beverage).getPrice();
        } else if (beverage instanceof OtherBeverages) {
            this.totalPrice += ((OtherBeverages) beverage).getPrice();
        }
        this.totalPrice += deliveryCharge; // Add delivery charge
    }

    // Getter for total price
    public double getTotalPrice() {
        return totalPrice;
    }

    //Getter for total price of all order items
    public static double getTotalCombinedPrice() {
        return totalCombinedPrice;
    }

    // Setter methods for beverage
    public void setBeverage(MatchaHut beverage) {
        this.beverage = beverage;
    }

    // Getter for beverage
    public MatchaHut getBeverage() {
        return beverage;
    }
   
    
    /* Write order details to file 
     * 
     * Preconditions:
     * - orderTime, customerName, and pickupMethod must be initialized.
     * - beverage must be a valid instance of Coffee, Tea, or OtherBeverages.
     * - isNewCustomer should correctly indicate whether the customer is new.
     * Postconditions:
     * - The delivery charge is applied only if it hasn’t been charged before.
     * - totalCombinedPrice is updated with the new order’s total.
     * - Order details are written to "order details.txt", overwriting if the customer is new, or appending otherwise.
     * - If an error occurs while writing, an error message is displayed.
     */
    public void writeOrderToFile(boolean isNewCustomer) {
        // Reset delivery charge and total combined price for a new customer
        if (isNewCustomer) {
            isDeliveryCharged = false;
            totalCombinedPrice = 0.0;
        }

        // Calculate delivery charge (only for the first order of the customer)
        double deliveryCharge = isDeliveryCharged ? 0.0 : getDeliveryCharge();
        if (!isDeliveryCharged) {
            isDeliveryCharged = true; // Mark delivery as charged
        }

        // Calculate the total price for this order
        double orderTotalPrice = getTotalPrice() + deliveryCharge;
        totalCombinedPrice += orderTotalPrice; // Add to the combined total

        // Build order details string
        String orderDetails = "Order Time: " + orderTime + "\n" +
            "Customer Name: " + customerName + "\n" +
            "Pickup Method: " + pickupMethod + "\n" +
            "Beverage Details:\n";

        // Add beverage-specific details
        if (beverage instanceof Coffee) {
            orderDetails += ((Coffee) beverage).getOrderDetails();
        } else if (beverage instanceof Tea) {
            orderDetails += ((Tea) beverage).getOrderDetails();
        } else if (beverage instanceof OtherBeverages) {
            orderDetails += ((OtherBeverages) beverage).getOrderDetails();
        }

        // Add delivery charge and total price for this order
        orderDetails += "\nDelivery Charge: $" + String.format("%.2f", deliveryCharge) +
        "\nOrder Total Price: $" + String.format("%.2f", orderTotalPrice) +
        "\nTotal Price (All Items): $" + String.format("%.2f", totalCombinedPrice) +
        "\n-----------------------------\n";

        // Write to file (append if not a new customer, overwrite if it's a new customer)
        try (FileWriter writer = new FileWriter("order details.txt", !isNewCustomer)) {
            writer.write(orderDetails);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    //Method to show final message after all order items are displayed
    public void displayFinalMessage(boolean orderMore, double totalCombinedPrice) {
        if (!orderMore) {
            System.out.printf("\n\nThat will be $%.2f. Thank You For Visiting Matcha Hut!\n", totalCombinedPrice);
            System.out.println("\nPlease Come Again!");
        }
    }
}