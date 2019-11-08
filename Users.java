import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Users {


    public static void InsertUser(int UserID, String FirstName, String LastName, String DateOfBirth, String Gender, int Age, String Username, String Password) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Users(UserID, FirstName, LastName, DateOfBirth, Gender, Age, Username, Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, UserID);
            ps.setString(2, FirstName);
            ps.setString(3, LastName);
            ps.setString(4, DateOfBirth);
            ps.setString(5, Gender);
            ps.setInt(6, Age);
            ps.setString(7, Username);
            ps.setString(8, Password);
            ps.executeUpdate();
            System.out.println();
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    public static void ListUser() {

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT UserID, FirstName, LastName, DateOfBirth, Gender, Age, Username, Password FROM Users");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int UserID = results.getInt(1);
                String FirstName = results.getString(2);
                String LastName = results.getString(3);
                String DateOfBirth = results.getString(4);
                String Gender = results.getString(5);
                int Age = results.getInt(6);
                String Username = results.getString(7);
                String Password = results.getString(8);
                System.out.print("UserID: " + UserID + "\n");
                System.out.print("FirstName: " + FirstName + "\n");
                System.out.print("LastName: " + LastName + "\n");
                System.out.print("DateOfBirth: " + DateOfBirth + "\n");
                System.out.print("Gender: " + Gender + "\n");
                System.out.print("Age: " + Age + "\n");
                System.out.print("Username: " + Username + "\n");
                System.out.print("Password: " + Password + "\n");

            }


        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    public static void UpdateUser(int UserID, String FirstName, String LastName, String DateOfBirth, String Gender, int Age, String Username, String Password) {

        try {

            PreparedStatement ps = Main.db.prepareStatement(
                    "UPDATE Users SET FirstName = ?, LastName = ?, DateOfBirth = ?, Gender = ?, Age = ?, Username = ?, Password = ? WHERE UserID = ?");

            ps.setString(1, FirstName);
            ps.setString(2, LastName);
            ps.setString(3, DateOfBirth);
            ps.setString(4, Gender);
            ps.setInt(5, Age);
            ps.setString(6, Username);
            ps.setString(7, Password);
            ps.setInt(8, UserID);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    public static void DeleteUser(int UserID) {

        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Users WHERE UserID = ?");

            ps.setInt(1, UserID);
            ps.execute();
            System.out.print("UserID: " + UserID+ " deleted \n");

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }


    }

}
