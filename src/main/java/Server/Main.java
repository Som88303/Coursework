package Server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {


    public static Connection db = null;

    public static void main(String[] args) {

        openDatabase("Coursework Database.db");
        ResourceConfig config = new ResourceConfig();
        config.packages("Controllers");
        config.register(MultiPartFeature.class);
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));

        Server server = new Server(8081);
        ServletContextHandler context = new ServletContextHandler(server, "/");
        context.addServlet(servlet, "/*");

        try {
            server.start();
            System.out.println("Server successfully started.");
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeDatabase();

    }

    private static void openDatabase(String dbFile) {
        try {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            db = DriverManager.getConnection("jdbc:sqlite:resources/" + dbFile, config.toProperties());
            System.out.println("Database connection successfully established.");
        } catch (Exception exception) {
            System.out.println("Database connection error: " + exception.getMessage());
        }

    }

    private static void closeDatabase() {
        try {
            db.close();
            System.out.println("Disconnected from database.");
        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }


    private static void getDogs() {

        try {

            PreparedStatement ps = db.prepareStatement("SELECT DogID, DogName, DOB FROM Dogs");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int DogID = results.getInt(1);
                String DogName = results.getString(2);
                String DOB = results.getString(3);
                System.out.println(DogID + " " + DogName + " " + DOB);
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }


    }

    private static void addDogs() {
        try {
            PreparedStatement ps = db.prepareStatement("INSERT INTO Dogs (DogID, DogName, DOB) VALUES (?, ?, ?)");

            ps.setInt(1, 6);
            ps.setString(2, "Bob");
            ps.setString(3, "19/2/2002");

            ps.executeUpdate();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

        closeDatabase();

    }

}




