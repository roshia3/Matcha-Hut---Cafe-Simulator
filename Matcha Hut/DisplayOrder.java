/*Class to Read from the order details file
 * And display the Order details to screen 
 * 
 * @Roshia_Dutta @version_1
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class DisplayOrder {
    /* Reads and displays order details from the "order details.txt" file.
     * 
     * Preconditions:
     * - The file "order details.txt" must exist in the directory.
     * - The file should contain order details in a readable format.
     * 
     * Postconditions:
     * - The order details are printed to the console if the file is found.
     * - If the file is missing or cannot be read, an error message is displayed.
     */
    public void displayOrderFromFile() {
        System.out.println("\n\nYour order details are:");
        System.out.println("-----------------------------");

        try (BufferedReader reader = new BufferedReader(new FileReader("order details.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

        System.out.println("-----------------------------");
    }
}
