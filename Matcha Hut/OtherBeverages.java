/* Class for Other beverages
 * Allows different customization for all drinks
 * 
 * @Roshia_Dutta @version_1
 */
import java.io.FileWriter;
import java.io.IOException;

public class OtherBeverages extends MatchaHut {
    private String beverageType;
    private boolean extraSweet; // For Strawberry Acai
    private boolean iced; // For Strawberry Acai and iced coffee
    private boolean extraBubbly; // For Orange Soda
    private boolean SodaIced; // For Orange Soda
    // For Matcha
    private boolean MatchaHot; 
    private boolean MatchaSweet;
    private boolean hotChocoWhip; 
    // For Hot Chocolate
    private boolean hotChocoMarshmellow;
    private double price; //For All drinks 

    /* Constructor to initialize attributes based on the beverage type 
     * 
     * Preconditions:
     * - size must be a valid beverage size.
     * - beverageType must be a valid string representing the type of beverage.
     * - Boolean values determine additional beverage options (sweetness, ice, bubbles, etc.).
     * 
     * Postconditions:
     * - The instance variables are initialized with the provided values.
     * - The parent class constructor is called to set the beverage type and size.
     * - The price of the beverage is calculated and set based on the options selected.
     */
    public OtherBeverages(String size, String beverageType, boolean extraSweet, boolean iced,
    boolean extraBubbly, boolean withIce, boolean MatchaHot,
    boolean MatchaSweet, boolean hotChocoWhip, boolean hotChocoMarshmellow) {

        /* Call the constructor of the parent class Beverage to set type to "Other Beverage" 
         * and initialize the size.
         */
        super("Other Beverage", size);
        this.beverageType = beverageType;
        this.extraSweet = extraSweet;
        this.iced = iced;
        this.extraBubbly = extraBubbly;
        this.SodaIced = withIce;
        this.MatchaHot = MatchaHot;
        this.MatchaSweet = MatchaSweet;
        this.hotChocoWhip = hotChocoWhip;
        this.hotChocoMarshmellow = hotChocoMarshmellow;
        setPrice(); // Calculate and set the price based on type and size
    }

    // Getter for beverage type
    public String getBeverageType() {
        return beverageType;
    }

    // Setter for beverage type
    public void setBeverageType(String beverageType) {
        this.beverageType = beverageType;
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

    // Getter for extraSweet
    public boolean isExtraSweet() {
        return extraSweet;
    }

    // Setter for extraSweet
    public void setExtraSweet(boolean extraSweet) {
        this.extraSweet = extraSweet;
    }

    // Getter for iced
    public boolean isIced() {
        return iced;
    }

    // Setter for iced
    public void setIced(boolean iced) {
        this.iced = iced;
    }

    // Getter for extraBubbly
    public boolean isExtraBubbly() {
        return extraBubbly;
    }

    // Setter for extraBubbly
    public void setExtraBubbly(boolean extraBubbly) {
        this.extraBubbly = extraBubbly;
    }

    // Getter for SodaIced
    public boolean isSodaIced() {
        return SodaIced;
    }

    // Setter for SodaIced
    public void setSodaIced(boolean SodaIced) {
        this.SodaIced = SodaIced;
    }

    // Getter for MatchaHot
    public boolean isMatchaHot() {
        return MatchaHot;
    }

    // Setter for MatchaHot
    public void setMatchaHot(boolean MatchaHot) {
        this.MatchaHot = MatchaHot;
    }

    // Getter for MatchaSweet
    public boolean isMatchaSweet() {
        return MatchaSweet;
    }

    // Setter for MatchaSweet
    public void setMatchaSweet(boolean MatchaSweet) {
        this.MatchaSweet = MatchaSweet;
    }

    // Getter for hotChocoWhip
    public boolean isHotChocoWhip() {
        return hotChocoWhip;
    }

    // Setter for hotChocoWhip
    public void setHotChocoWhip(boolean hotChocoWhip) {
        this.hotChocoWhip = hotChocoWhip;
    }

    // Getter for hotChocoMarshmellow
    public boolean isHotChocoMarshmellow() {
        return hotChocoMarshmellow;
    }

    // Setter for hotChocoMarshmellow
    public void setHotChocoMarshmellow(boolean hotChocoMarshmellow) {
        this.hotChocoMarshmellow = hotChocoMarshmellow;
    }

    // Method to calculate and set the price based on type and size
    private void setPrice() {
        double basePrice = 0.0;
        double sizeMultiplier = 1.0;

        // Set base price based on beverage type
        switch (beverageType.toLowerCase()) {
            case "hot chocolate":
                basePrice = 3.00;
                break;
            case "matcha latte":
                basePrice = 4.00;
                break;
            case "iced coffee":
                basePrice = 3.25;
                break;
            case "strawberry açai juice":
                basePrice = 4.50;
                break;
            case "orange soda":
                basePrice = 3.75;
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
        String orderDetails = "Other Beverage Order: " + size + " " + beverageType;
        if (beverageType.equalsIgnoreCase("Strawberry Açai Juice")) {
            orderDetails += "\n- Extra Sweet: " + (extraSweet ? "Yes" : "No") +
            "\n- Iced: " + (iced ? "Yes" : "No");
        } else if (beverageType.equalsIgnoreCase("Orange Soda")) {
            orderDetails += "\n- Extra Bubbly: " + (extraBubbly ? "Yes" : "No") +
            "\n- With Ice: " + (SodaIced ? "Yes" : "No");
        } else if (beverageType.equalsIgnoreCase("Hot Chocolate")) {
            orderDetails += "\n- Whipped Cream: " + (hotChocoWhip ? "Yes" : "No") +
            "\n- Marshmallow: " + (hotChocoMarshmellow ? "Yes" : "No");
        } else if (beverageType.equalsIgnoreCase("Matcha Latte")) {
            orderDetails += "\n- Extra Matcha: " + (MatchaSweet ? "Yes" : "No") +
            "\n- Hot/Cold: " + (MatchaHot ? "Hot" : "Cold");
        } else if (beverageType.equalsIgnoreCase("Iced Coffee")) {
            orderDetails += "\n- Extra Ice: " + (iced ? "Yes" : "No") +
            "\n- Sweetened: " + (extraSweet ? "Yes" : "No");
        }

        orderDetails += String.format("\nPrice: $%.2f", price);

        return orderDetails;
    }

    // Display other beverage details
    public void displayOrder() {
        String orderDetails = getOrderDetails();

        // Write to order file
        try (FileWriter writer = new FileWriter("order details.txt", true)) {
            writer.write(orderDetails);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}