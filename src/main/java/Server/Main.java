package Server;

import java.sql.*;
import java.util.List;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.sqlite.SQLiteConfig;

import javax.sound.sampled.Port;

public class Main {


    public static Connection db = null;

    public static void main(String[] args) {

        openDatabase("Coursework Database.db"); // connecting to the database

        ResourceConfig config = new ResourceConfig(); //Preparing the Jersey Servlet
        config.packages("Controllers"); //Use handlers in the "Controllers" Package
        config.register(MultiPartFeature.class); //This will help support multipart HTML forms
        ServletHolder servlet = new ServletHolder(new ServletContainer(config)); // This starts the Servlet

        Server server = new Server(8081); // This prepares the Jetty server to listen on port 8081
        ServletContextHandler context = new ServletContextHandler(server, "/"); // This instantiates the server
        context.addServlet(servlet, "/*"); // Connect the servlet to the server

        try {
            server.start(); // Starts the server
            System.out.println("Server successfully started."); // Displays success message if it has worked
            server.join(); //
        } catch (Exception e) {
            e.printStackTrace(); //Error checking
        }
    }


    public static void openDatabase(String dbFile) {
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

    public static void closeDatabase() {
        try {
            db.close();
            //closes database file
            System.out.println("Disconnected from database.");
        } catch (Exception exception) {
            //catches errors & prints message rather then closing program
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }















}