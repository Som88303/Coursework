import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Consumed {

    public static void InsertConsumed() {
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Consumed(ConsumedID, FoodID, UserID, MealType, PortionSize, DateEaten, HealthyPoints) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, 1);
            ps.setInt(2, 1);
            ps.setInt(3, 1);
            ps.setString(4, "Breakfast");
            ps.setInt(5, 124);
            ps.setString(6, "7/11/19");
            ps.setInt(7, 1);
            ps.executeUpdate();
            System.out.println();
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    public static void ListConsumed() {

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT ConsumedID, FoodID, UserID, MealType, PortionSize, DateEaten, HealthyPoints FROM Consumed");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int ConsumedID = results.getInt(1);
                int FoodID = results.getInt(2);
                int UserID = results.getInt(3);
                String MealType = results.getString(4);
                int PortionSize = results.getInt(5);
                String DateEaten = results.getString(6);
                int HealthyPoints = results.getInt(7);
                System.out.print("ConsumedID: " + ConsumedID + "\n");
                System.out.print("FoodID: " + FoodID + "\n");
                System.out.print("UserID: " + UserID + "\n");
                System.out.print("MealType: " + MealType + "\n");
                System.out.print("PortionSize: " + PortionSize + "\n");
                System.out.print("DateEaten: " + DateEaten + "\n");
                System.out.print("HealthyPoints: " + HealthyPoints + "\n");
            }


        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }


    }

    public static void UpdateConsumed(int ConsumedID, int FoodID, int UserID, String MealType, int PortionSize, String DateEaten, int HealthyPoints) {

        try {

            PreparedStatement ps = Main.db.prepareStatement(
                    "UPDATE Consumed SET FoodID = ?, UserID = ?, MealType = ?, PortionSize = ?, DateEaten = ?, HealthyPoints = ? WHERE ConsumedID = ?");

            ps.setInt(1, FoodID);
            ps.setInt(2, UserID);
            ps.setString(3, MealType);
            ps.setInt(4, PortionSize);
            ps.setString(5, DateEaten);
            ps.setInt(6, HealthyPoints);
            ps.setInt(7, ConsumedID);
            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    public static void DeleteConsumed(int ConsumedID) {

        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Consumed WHERE ConsumedID = ?");

            ps.setInt(1, ConsumedID);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

    }


}
