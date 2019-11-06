import java.sql.*;
import java.util.List;

import org.sqlite.SQLiteConfig;

import javax.sound.sampled.Port;

public class Main {


    public static Connection db = null;

    public static void main(String[] args) {
        openDatabase("Coursework Database.db");
        // code to get data from, write to the database etc goes here

        DeleteConsumed(1);
        DeleteFood(1);
        DeleteUser(1);
        DeleteSession(1);
        DeleteExercise(1);
        InsertFood();
        InsertUser();
        InsertExercise();
        InsertConsumed();
        InsertSession();

        ListFood();
        ListUser();
        ListExercise();
        ListConsumed();
        ListSession();

        closeDatabase();
    }

    private static void openDatabase(String dbFile) {
        try {
            Class.forName("org.sqlite.JDBC");
            //loads the database driver
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            //establishes database settings (maintains structural integrity)
            db = DriverManager.getConnection("jdbc:sqlite:resources/" + dbFile, config.toProperties());
            //opens database file
            System.out.println("Database connection successfully established.");
        } catch (Exception exception) {
            //catches errors & prints message rather then closing program
            System.out.println("Database connection error: " + exception.getMessage());
        }

    }

    private static void closeDatabase() {
        try {
            db.close();
            //closes database file
            System.out.println("Disconnected from database.");
        } catch (Exception exception) {
            //catches errors & prints message rather then closing program
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }



    private static void InsertUser() {
        try {
            PreparedStatement ps = db.prepareStatement("INSERT INTO Users(UserID, FirstName, LastName, DateOfBirth, Gender, Age, Username, Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, 1);
            ps.setString(2, "Som");
            ps.setString(3, "Gurung");
            ps.setString(4, "1/07/2002");
            ps.setString(5, "Male");
            ps.setInt(6, 17);
            ps.setString(7, "SomGurung");
            ps.setString(8, "GurungSom");
            ps.executeUpdate();
            System.out.println();
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    private static void ListUser() {

        try {

            PreparedStatement ps = db.prepareStatement("SELECT UserID, FirstName, LastName, DateOfBirth, Gender, Age, Username, Password FROM Users");

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

    private static void UpdateUser(int UserID, String FirstName, String LastName, String DateOfBirth, String Gender, int Age, String Username, String Password) {

        try {

            PreparedStatement ps = db.prepareStatement(
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

    private static void DeleteUser(int UserID) {

        try {

            PreparedStatement ps = db.prepareStatement("DELETE FROM Users WHERE UserID = ?");

            ps.setInt(1, UserID);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }


    }




    private static void InsertFood() {
        try {
            PreparedStatement ps = db.prepareStatement("INSERT INTO Foods(FoodID, Foodname, Proteins, Carbohydrates, Fats, CalPerHundredGrams, HealthyPoints, PortionSize) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, 1);
            ps.setString(2, "Chicken");
            ps.setInt(3, 23);
            ps.setInt(4, 14);
            ps.setInt(5, 124);
            ps.setInt(6, 100);
            ps.setInt(7, 1);
            ps.setInt(8, 100);
            ps.executeUpdate();
            System.out.println();
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    private static void ListFood() {

        try {

            PreparedStatement ps = db.prepareStatement("SELECT FoodID, Foodname, Proteins, Carbohydrates, Fats, CalPerHundredGrams, HealthyPoints, PortionSize FROM Foods");

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

    private static void UpdateFood(int FoodID, String FoodName, int Proteins, int Carbohydrates, int Fats, int CalPerHundredGrams, int HealthyPoints, int PortionSize) {

        try {

            PreparedStatement ps = db.prepareStatement(
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

    private static void DeleteFood(int FoodID) {

        try {

            PreparedStatement ps = db.prepareStatement("DELETE FROM Foods WHERE FoodID = ?");

            ps.setInt(1, FoodID);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

    }




    private static void InsertConsumed() {
        try {
            PreparedStatement ps = db.prepareStatement("INSERT INTO Consumed(ConsumedID, FoodID, UserID, MealType, PortionSize, DateEaten, HealthyPoints) VALUES (?, ?, ?, ?, ?, ?, ?)");
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

    private static void ListConsumed() {

        try {

            PreparedStatement ps = db.prepareStatement("SELECT ConsumedID, FoodID, UserID, MealType, PortionSize, DateEaten, HealthyPoints FROM Consumed");

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

    private static void UpdateConsumed(int ConsumedID, int FoodID, int UserID, String MealType, int PortionSize, String DateEaten, int HealthyPoints) {

        try {

            PreparedStatement ps = db.prepareStatement(
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

    private static void DeleteConsumed(int ConsumedID) {

        try {

            PreparedStatement ps = db.prepareStatement("DELETE FROM Consumed WHERE ConsumedID = ?");

            ps.setInt(1, ConsumedID);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

    }




    private static void InsertExercise() {
        try {
            PreparedStatement ps = db.prepareStatement("INSERT INTO Exercises(ExerciseID, ExerciseName, CalPerHour) VALUES (?, ?, ?)");
            ps.setInt(1, 1);
            ps.setString(2, "Jumping Jacks");
            ps.setInt(3, 123);
            ps.executeUpdate();
            System.out.println();
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    private static void ListExercise() {

        try {

            PreparedStatement ps = db.prepareStatement("SELECT ExerciseID, ExerciseName, CalPerHour FROM Exercises");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int ExerciseID = results.getInt(1);
                String ExerciseName = results.getString(2);
                int CalPerHour = results.getInt(3);
                System.out.print("ExerciseID: " + ExerciseID + "\n");
                System.out.print("ExerciseName: " + ExerciseName + "\n");
                System.out.print("CalPerHour: " + CalPerHour + "\n");

            }


        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }


    }

    private static void UpdateExercise(int ExerciseID, String ExerciseName, int CalPerHour) {

        try {

            PreparedStatement ps = db.prepareStatement(
                    "UPDATE Exercises SET ExerciseName = ? , CalPerHour = ?  WHERE ExerciseID = ?");

            ps.setString(1, ExerciseName);
            ps.setInt(2, CalPerHour);
            ps.setInt(3, ExerciseID);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    private static void DeleteExercise(int ExerciseID) {

        try {

            PreparedStatement ps = db.prepareStatement("DELETE FROM Exercises WHERE ExerciseID = ?");

            ps.setInt(1, ExerciseID);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

    }




    private static void InsertSession() {
        try {
            PreparedStatement ps = db.prepareStatement("INSERT INTO Sessions(SessionID, ExerciseID, UserID, DateExercised, Duration) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, 1);
            ps.setInt(2, 1);
            ps.setInt(3, 1);
            ps.setString(4, "Jumping Jacks");
            ps.setInt(5, 123);
            ps.executeUpdate();
            System.out.println();
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    private static void ListSession() {

        try {

            PreparedStatement ps = db.prepareStatement("SELECT SessionID, ExerciseID, UserID, DateExercised, Duration FROM Sessions");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int SessionID = results.getInt(1);
                int ExerciseID = results.getInt(2);
                int UserID = results.getInt(3);
                String DateExercised = results.getString(4);
                int Duration = results.getInt(5);
                System.out.print("SessionID: " + SessionID + "\n");
                System.out.print("ExerciseID: " + ExerciseID + "\n");
                System.out.print("UserID: " + UserID + "\n");
                System.out.print("DateExercised: " + DateExercised + "\n");
                System.out.print("Duration: " + Duration + "\n");

            }


        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }


    }

    private static void UpdateSession(int SessionID, int ExerciseID, int UserID, String DateExercised, int Duration) {

        try {

            PreparedStatement ps = db.prepareStatement(
                    "UPDATE Sessions SET ExcerciseID = ?, UserID = ?, DateExcercised = ?, Duration = ?  WHERE SessionID = ?");

            ps.setInt(1, ExerciseID);
            ps.setInt(2, UserID);
            ps.setString(3, DateExercised);
            ps.setInt(4, Duration);
            ps.setInt(5, SessionID);
            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    private static void DeleteSession(int SessionID) {

        try {

            PreparedStatement ps = db.prepareStatement("DELETE FROM Sessions WHERE SessionID = ?");

            ps.setInt(1, SessionID);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

    }
}