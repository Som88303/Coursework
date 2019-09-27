package Controllers;

import Server.Main;
import com.sun.jersey.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@Path("Users")
public class Users {
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public static String listUsers() {
        System.out.println("Users list");
        JSONArray list = new JSONArray();
        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT UserID,FirstName,LastName,DateOfBirth,Gender,Age,Username,Password FROM Users");
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                JSONObject User = new JSONObject();
                User.put("UserID", results.getInt(1));
                User.put("FirstName", results.getString(2));
                User.put("LastName", results.getString(3));
                User.put("DateOfBirth", results.getString(4));
                User.put("Gender", results.getString(5));
                User.put("Age", results.getInt(6));
                User.put("Username", results.getString(7));
                User.put("Password", results.getString(8));
                list.add(User);
            }
            return list.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to list items, please see server console for more info.\"}";
        }
    }
}
