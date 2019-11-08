import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Foods {

    public static void ListFood() {

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT FoodID, Foodname, Proteins, Carbohydrates, Fats, CalPerHundredGrams, HealthyPoints, PortionSize FROM Foods");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int FoodID = results.getInt(1);
                String FoodName = results.getString(2);
                int Proteins = results.getInt(3);
                int Carbohydrates = results.getInt(4);
                int Fats = results.getInt(5);
                int CalPerHundredGrams = results.getInt(6);
                int HealthyPoints = results.getInt(7);
                int PortionSize = results.getInt(8);
                System.out.print("FoodID: " + FoodID + "\n");
                System.out.print("FoodName: " + FoodName + "\n");
                System.out.print("Proteins: " + Proteins + "\n");
                System.out.print("Carbohydrates: " + Carbohydrates + "\n");
                System.out.print("Fats: " + Fats + "\n");
                System.out.print("CalPerHundredGrams: " + CalPerHundredGrams + "\n");
                System.out.print("HealthyPoints: " + HealthyPoints + "\n");
                System.out.print("PortionSize: " + PortionSize + "\n");
            }


        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }


    }

    public static void UpdateFood(int FoodID, String FoodName, int Proteins, int Carbohydrates, int Fats, int CalPerHundredGrams, int HealthyPoints, int PortionSize) {

        try {

            PreparedStatement ps = Main.db.prepareStatement(
                    "UPDATE Foods SET FoodName = ?, Proteins = ? , Carbohydrates = ? , Fats = ? , CalPerHundredGrams = ? , HealthyPoints = ? , PortionSize = ?  WHERE FoodID = ?");

            ps.setString(1, FoodName);
            ps.setInt(2, Proteins);
            ps.setInt(3, Carbohydrates);
            ps.setInt(4, Fats);
            ps.setInt(5, CalPerHundredGrams);
            ps.setInt(7, HealthyPoints);
            ps.setInt(8, PortionSize);
            ps.setInt(8, FoodID);
            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    public static void InsertFood(int FoodID, String Foodname, int Proteins, int Carbohydrates, int Fats, int CalPerHundredGrams, int HealthyPoints, int PortionSize) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Foods(FoodID, Foodname, Proteins, Carbohydrates, Fats, CalPerHundredGrams, HealthyPoints, PortionSize) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, FoodID);
            ps.setString(2, Foodname);
            ps.setInt(3, Proteins);
            ps.setInt(4, Carbohydrates);
            ps.setInt(5, Fats);
            ps.setInt(6, CalPerHundredGrams);
            ps.setInt(7, HealthyPoints);
            ps.setInt(8, PortionSize);
            ps.executeUpdate();
            System.out.println();
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }


    public static void DeleteFood(int FoodID) {

        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Foods WHERE FoodID = ?");

            ps.setInt(1, FoodID);
            ps.execute();
            System.out.print("FoodID: " + FoodID+ " deleted \n");


        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

    }

}
