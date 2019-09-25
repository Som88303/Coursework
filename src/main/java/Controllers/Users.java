package Controllers;

import Server.Main;
import com.sun.jersey.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Server.Main.db;
class ListUsers {
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public static String listUsers() {
        System.out.println("things/list");
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
class InsertUser {
    @POST
    @Path("new")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertThing(
            @FormDataParam("id") Integer id, @FormDataParam("name") String name, @FormDataParam("quantity") Integer quantity) {
        try {
            if (id == null || name == null || quantity == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("thing/new id=" + id);

            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Things (Id, Name, Quantity) VALUES (?, ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, quantity);
            ps.execute();
            return "{\"status\": \"OK\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to create new item, please see server console for more info.\"}";
        }

    }
}

class UpdateUser{
    @POST
    @Path("update")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateThing(
            @FormDataParam("id") Integer id, @FormDataParam("name") String name, @FormDataParam("quantity") Integer quantity) {
        try {
            if (id == null || name == null || quantity == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("thing/update id=" + id);

            PreparedStatement ps = Main.db.prepareStatement("UPDATE Things SET Name = ?, Quantity = ? WHERE Id = ?");
            ps.setString(1, name);
            ps.setInt(2, quantity);
            ps.setInt(3, id);
            ps.execute();
            return "{\"status\": \"OK\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";
        }
    }

}
class DeleteUser{
    @POST
    @Path("delete")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteThing(@FormDataParam("UserID") Integer UserID) {

        try {
            if (UserID == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("thing/delete id=" + UserID);

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Users WHERE Id = ?");

            ps.setInt(1, UserID);

            ps.execute();

            return "{\"status\": \"OK\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
        }
    }

}