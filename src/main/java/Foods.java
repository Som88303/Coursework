import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Foods {

    public static void InsertFood(int FoodID, String Foodname, int Proteins, int Carbohydrates, int Fats, int CalPerHundredGrams, int HealthyPoints, int PortionSize) {
        // Takes in parameters given by the user for the corresponding attributes of the table Foods
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Foods(FoodID, Foodname, Proteins, Carbohydrates, Fats, CalPerHundredGrams, HealthyPoints, PortionSize) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            // Asks for corresponding attributes
            ps.setInt(1, FoodID);
            ps.setString(2, Foodname);
            ps.setInt(3, Proteins);
            ps.setInt(4, Carbohydrates);                // Gives back the attributes asked for by the statement
            ps.setInt(5, Fats);
            ps.setInt(6, CalPerHundredGrams);
            ps.setInt(7, HealthyPoints);
            ps.setInt(8, PortionSize);
            ps.executeUpdate();                                       // Finally, adds the Food given by the the user
            System.out.println();
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
            // If something goes wrong with the code this error message comes up
        }
    }


    public static void ListFood() {

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT FoodID, Foodname, Proteins, Carbohydrates, Fats, CalPerHundredGrams, HealthyPoints, PortionSize FROM Foods");
            // Selects attributes from the table Foods
            ResultSet results = ps.executeQuery();
            // helps retrieve and modify the data inside the database
            while (results.next()) {
                int FoodID = results.getInt(1);
                String FoodName = results.getString(2);
                int Proteins = results.getInt(3);
                int Carbohydrates = results.getInt(4);              //sets the attributes to local variables to then print later
                int Fats = results.getInt(5);
                int CalPerHundredGrams = results.getInt(6);
                int HealthyPoints = results.getInt(7);
                int PortionSize = results.getInt(8);
                System.out.print("FoodID: " + FoodID + "\n");
                System.out.print("FoodName: " + FoodName + "\n");
                System.out.print("Proteins: " + Proteins + "\n");
                System.out.print("Carbohydrates: " + Carbohydrates + "\n");
                System.out.print("Fats: " + Fats + "\n");                       // Lists off all of the attributes of the table Foods
                System.out.print("CalPerHundredGrams: " + CalPerHundredGrams + "\n");
                System.out.print("HealthyPoints: " + HealthyPoints + "\n");
                System.out.print("PortionSize: " + PortionSize + "\n");
            }


        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            // If something goes wrong with the code this error message comes up
        }


    }

    public static void UpdateFood(int FoodID, String FoodName, int Proteins, int Carbohydrates, int Fats, int CalPerHundredGrams, int HealthyPoints, int PortionSize) {
            // Takes in parameters given by the user for the corresponding attributes of the table Foods
        try {

            PreparedStatement ps = Main.db.prepareStatement(
                    "UPDATE Foods SET FoodName = ?, Proteins = ? , Carbohydrates = ? , Fats = ? , CalPerHundredGrams = ? , HealthyPoints = ? , PortionSize = ?  WHERE FoodID = ?");
                    // Updates the attributes in the table Foods for the corresponding FoodID given by the user
            ps.setString(1, FoodName);
            ps.setInt(2, Proteins);
            ps.setInt(3, Carbohydrates);
            ps.setInt(4, Fats);                 // Sets the parameters to the corresponding index in the table
            ps.setInt(5, CalPerHundredGrams);
            ps.setInt(7, HealthyPoints);
            ps.setInt(8, PortionSize);
            ps.setInt(8, FoodID);
            ps.execute();                               // Finally, sets the given parameters in the database itself

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            // If something goes wrong with the code this error message comes up
        }
    }

    public static void DeleteFood(int FoodID) {
        // Asks for a parameter to the Food the user wants to delete(FoodID)
        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Foods WHERE FoodID = ?");
            // Deletes all attributes that associate with the given parameter by the user

            ps.setInt(1, FoodID);       // Gives the statement which FoodID to delete
            ps.execute();                           // Finally, deletes the Food entered by the user
            System.out.print("FoodID: " + FoodID+ " deleted \n");


        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            // If something goes wrong with the code this error message comes up
        }

    }

}
