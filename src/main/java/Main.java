import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.sqlite.SQLiteConfig;
public class Main {


    public static Connection db = null;

    public static void main(String[] args) {
        openDatabase("Coursework Database.db");
        // code to get data from, write to the database etc goes here
        insertUser();

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
    private static void closeDatabase(){
        try {
            db.close();
            //closes database file
            System.out.println("Disconnected from database.");
        } catch (Exception exception) {
            //catches errors & prints message rather then closing program
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }

    private static void insertUser() {
        try {
            PreparedStatement ps = db.prepareStatement("INSERT INTO Users(UserID, FirstName, LastName, DateOfBirth, Gender, Age, Username, Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1,1);
            ps.setString(2,"Som");
            ps.setString(3,"Gurung");
            ps.setString(4,"1/07/2002");
            ps.setString(5,"Male");
            ps.setInt(6,17);
            ps.setString(7,"SomGurung");
            ps.setString(8,"GurungSom");
            ps.executeUpdate();
            System.out.println();
        } catch (Exception exception) {
            System.out.println("Error: "+ exception.getMessage());
        }
    }


}