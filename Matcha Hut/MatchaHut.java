/* Base class of a coffee simulator
 * Instance variable: Beverage name and size
 * Also displays the menu.
 * 
 * @Roshia_Dutta @version_1
 */
import java.util.*;
import java.io.*;

public class MatchaHut {
    protected String beverageName;
    protected String size;
    
    /* Constructor
     * @param: String beverageName, String size
     * initializes parameters
     */
    public MatchaHut(String beverageName, String size) {
        this.beverageName = beverageName;
        this.size = size;
        
    }
    
     
    /* Displays menu, reading from menu file
     * 
     * Preconditions:
     * - The file "MatchaHut Menu.txt" must exist in the correct directory.
     * - The file must be readable and contain menu items in text format.
     * 
     * Postconditions:
     * - The contents of the menu file are printed to the console.
     * - If the file is missing or an error occurs, an error message is displayed.
     */
    
    public static void displayMenu() {
        String FilePath = "MatchaHut Menu.txt";
        //Read from file using Buffer Reader
        try (BufferedReader reader = new BufferedReader(new FileReader(FilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) { //if file not found
            System.err.println("Error reading menu file: " + e.getMessage());
        }
    }

    }
    
    

