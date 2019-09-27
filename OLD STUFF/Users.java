/*
package Controllers;

import Server.Main;
import com.sun.jersey.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class ListUsers {
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
class InsertUser {
    @POST
    @Path("new")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertUser(
            @FormDataParam("UserID") Integer UserID, @FormDataParam("FirstName") String FirstName, @FormDataParam("LastName") String LastName, @FormDataParam("DateOfBirth") String DateOfBirth, @FormDataParam("Gender") String Gender, @FormDataParam("Age") Integer Age,@FormDataParam("Username") String Username, @FormDataParam("Password") String Password){
        try {
            if (UserID == null || FirstName == null || LastName == null || DateOfBirth == null || Gender == null || Age == null || Username == null || Password == null ) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("User/new UserID=" + UserID);

            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Users (UserID,FirstName,LastName,DateOfBirth,Gender,Age,Username,Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, UserID);
            ps.setString(2, FirstName);
            ps.setString(3, LastName);
            ps.setString(4, DateOfBirth);
            ps.setString(5, Gender);
            ps.setInt(6, Age);
            ps.setString(7, Username);
            ps.setString(8, Password);
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
    public String updateUser(
            @FormDataParam("UserID") Integer UserID, @FormDataParam("FirstName") String FirstName, @FormDataParam("LastName") String LastName, @FormDataParam("DateOfBirth") String DateOfBirth, @FormDataParam("Gender") String Gender, @FormDataParam("Age") Integer Age,@FormDataParam("Username") String Username, @FormDataParam("Password") String Password){
        try {
            if (UserID == null || FirstName == null || LastName == null || DateOfBirth == null || Gender == null || Age == null || Username == null || Password == null ) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("User/new id=" + UserID);

            PreparedStatement ps = Main.db.prepareStatement("UPDATE Users SET FirstName = ?,LastName = ?,DateOfBirth = ?,Gender = ?,Age = ?,Username = ?,Password = ? WHERE UserID = ?");
            ps.setString(1, FirstName);
            ps.setString(2, LastName);
            ps.setString(3, DateOfBirth);
            ps.setString(4, Gender);
            ps.setInt(5, Age);
            ps.setString(6, Username);
            ps.setString(7, Password);
            ps.setInt(8, UserID);
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
    public String deleteUser(@FormDataParam("UserID") Integer UserID) {

        try {
            if (UserID == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("User/delete UserID=" + UserID);

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Users WHERE UserID = ?");

            ps.setInt(1, UserID);

            ps.execute();

            return "{\"status\": \"OK\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
        }
    }

}

 */