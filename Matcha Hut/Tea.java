/* Class for Tea beverages
 * Allows different customization for all drinks
 * 
 * @Roshia_Dutta @version_1
 */
import java.io.FileWriter;
import java.io.IOException;

class Tea extends MatchaHut {
    private String teaType;
    private boolean withLemon;
    private boolean withHalfAndHalf;
    private boolean withSugar;
    private double price;

    /* Constructor
     * @param: String size, String teaType
     * @param: boolean withLemon, withHalfAndHalf, withSugar
     * initializes parameters
     */
    
    public Tea(String size, String teaType, boolean withLemon, boolean withHalfAndHalf, boolean withSugar) {
        super("Tea", size); //type and Default size
        this.teaType = teaType;
        this.withLemon = withLemon;
        this.withHalfAndHalf = withHalfAndHalf;
        this.withSugar = withSugar;
        setPrice(); // Calculate and set the price based on type and size
    }

    // Getter for tea type
    public String getTeaType() {
        return teaType;
    }

    // Setter for tea type
    public void setTeaType(String teaType) {
        this.teaType = teaType;
        setPrice(); // Recalculate price when tea type changes
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

    // Getter for withLemon
    public boolean isWithLemon() {
        return withLemon;
    }

    // Setter for withLemon
    public void setWithLemon(boolean withLemon) {
        this.withLemon = withLemon;
    }

    // Getter for withHalfAndHalf
    public boolean isWithHalfAndHalf() {
        return withHalfAndHalf;
    }

    // Setter for withHalfAndHalf
    public void setWithHalfAndHalf(boolean withHalfAndHalf) {
        this.withHalfAndHalf = withHalfAndHalf;
    }

    // Getter for withSugar
    public boolean isWithSugar() {
        return withSugar;
    }

    // Setter for withSugar
    public void setWithSugar(boolean withSugar) {
        this.withSugar = withSugar;
    }

    // Set the price based on type and size
    private void setPrice() {
        double basePrice = 0.0;
        double sizeMultiplier = 1.0;

        // Set base price based on tea type
        switch (teaType.toLowerCase()) {
            case "green tea":
                basePrice = 2.00;
                break;
            case "black tea":
                basePrice = 2.25;
                break;
            case "chai latte":
                basePrice = 3.50;
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
        String orderDetails = "Tea Order: " + size + " " + teaType +
                                (withLemon ? ", with lemon" : "") +
                                (withHalfAndHalf ? ", with half-and-half" : "") +
                                (withSugar ? ", with sugar" : "") +
                                 String.format("\nPrice: $%.2f", price);
                                 
        return orderDetails;
    }
    
    // Display tea details
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