package Controllers;

import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;


@Path("Users")
public class Users {
        @POST
        @Path("list")
        @Consumes(MediaType.MULTIPART_FORM_DATA)
        @Produces(MediaType.APPLICATION_JSON)
        public String ListUser() {
            System.out.println("Users/list");
            JSONArray list = new JSONArray();

            try {

                PreparedStatement ps = Main.db.prepareStatement("SELECT UserID, FirstName, LastName, DateOfBirth, Gender, Age, Username, Password FROM Users");

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


        @POST
        @Path("add")
        @Consumes(MediaType.MULTIPART_FORM_DATA)
        @Produces(MediaType.APPLICATION_JSON)
        public String InsertUser(@FormDataParam("FirstName") String FirstName, @FormDataParam("LastName") String LastName,
                                 @FormDataParam("DateOfBirth") String DateOfBirth, @FormDataParam("Gender") String Gender, @FormDataParam("Age") Integer Age,
                                 @FormDataParam("Username") String Username, @FormDataParam("Password") String Password) {
            try {
                if (FirstName == null || LastName == null || DateOfBirth == null || Gender == null || Age == null || Username == null || Password == null) {
                    throw new Exception("One or more form data parameters are missing in the HTTP request.");
                }
                PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Users(FirstName, LastName, DateOfBirth, Gender, Age, Username, Password) VALUES (?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, FirstName);
                ps.setString(2, LastName);
                ps.setString(3, DateOfBirth);
                ps.setString(4, Gender);
                ps.setInt(5, Age);
                ps.setString(6, Username);
                ps.setString(7, Password);
                ps.executeUpdate();
                return "{\"status\": \"OK\"}";
            } catch (Exception exception) {
                System.out.println("Error: " + exception.getMessage());
                return "{\"error\": \"Unable to create new item, please see server console for more info.\"}";
            }
        }


        @POST
        @Path("update")
        @Consumes(MediaType.MULTIPART_FORM_DATA)
        @Produces(MediaType.APPLICATION_JSON)
        public String UpdateFood(@FormDataParam("UserID") Integer UserID, @FormDataParam("FirstName") String FirstName, @FormDataParam("LastName") String LastName,
                                 @FormDataParam("DateOfBirth") String DateOfBirth, @FormDataParam("Gender") String Gender, @FormDataParam("Age") Integer Age,
                                 @FormDataParam("Username") String Username, @FormDataParam("Password") String Password) {
            // Takes in parameters given by the user for the corresponding attributes of the table Controllers.Foods
            try {
                if (UserID == null || FirstName == null || LastName == null || DateOfBirth == null || Gender == null || Age == null || Username == null || Password == null) {
                    throw new Exception("One or more form data parameters are missing in the HTTP request.");
                }
                PreparedStatement ps = Main.db.prepareStatement(
                        "UPDATE Users SET FirstName = ?, LastName = ?, DateOfBirth = ?, Gender = ?, Age = ?, Username = ?, Password = ?  WHERE UserID = ?");
                // Updates the attributes in the table Controllers.Foods for the corresponding FoodID given by the user
                ps.setString(1, FirstName);
                ps.setString(2, LastName);
                ps.setString(3, DateOfBirth);
                ps.setString(4, Gender);
                ps.setInt(5, Age);
                ps.setString(6, Username);
                ps.setString(7, Password);
                ps.setInt(8, UserID);
                ps.executeUpdate();                               // Finally, sets the given parameters in the database itself
                return "{\"status\": \"OK\"}";
            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
                // If something goes wrong with the code this error message comes up
                return "{\"error\": \"Unable to update item, please see server console for more info.\"}";
            }
        }

        @POST
        @Path("delete")
        @Consumes(MediaType.MULTIPART_FORM_DATA)
        @Produces(MediaType.APPLICATION_JSON)
        public String DeleteUser(@FormDataParam("UserID") Integer UserID) {

            try {
                if (UserID == null) {
                    throw new Exception("One or more form data parameters are missing in the HTTP request.");
                }
                System.out.println("Users/delete" + UserID);
                PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Users WHERE UserID = ?");
                ps.setInt(1, UserID);
                ps.execute();
                System.out.print("UserID: " + UserID + " deleted \n");
                return "{\"status\": \"OK\"}";

            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
                return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
            }
        }


    @GET
    @Path("get/{UserID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String GetUser(@PathParam("UserID") Integer UserID) throws Exception {
        if (UserID == null) {
            throw new Exception("User 'ID' is missing in the HTTP request's URL.");
        }
        System.out.println("UserID/get/" + UserID);
        JSONObject Users = new JSONObject();
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT FirstName, LastName, DateOfBirth, Gender, Age, Username, Password FROM Users WHERE UserID = ?");
            ps.setInt(1, UserID);
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                Users.put("FoodID", UserID);
                Users.put("FirstName", results.getString(1));
                Users.put("LastName", results.getString(2));
                Users.put("DateOfBirth", results.getString(3));
                Users.put("Gender", results.getString(4));
                Users.put("Age", results.getInt(1));
                Users.put("Username", results.getString(2));
                Users.put("Password", results.getString(3));
            }
            return Users.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to get item, please see server console for more info.\"}";
        }
    }

    @POST
    @Path("login")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String loginUser(@FormDataParam("username") String username, @FormDataParam("password") String password) {
            try {
                PreparedStatement ps1 = Main.db.prepareStatement("SELECT Password FROM Users WHERE Username = ?");
                ps1.setString(1, username);
                ResultSet loginResults = ps1.executeQuery();
                if (loginResults.next()) {

                    String correctPassword = loginResults.getString(1);
                    System.out.println(correctPassword);
                    if (password.equals(correctPassword)) {

                        String token = UUID.randomUUID().toString();

                        PreparedStatement ps2 = Main.db.prepareStatement("UPDATE Users SET Token = ? WHERE Username = ?");
                        ps2.setString(1, token);
                        ps2.setString(2, username);
                        ps2.executeUpdate();

                        return "{\"token\": \"" + token + "\"}";

                    } else {

                        return "{\"error\": \"Incorrect password!\"}";

                    }

                } else {

                    return "{\"error\": \"Unknown user!\"}";

                }

            } catch (Exception exception) {
                System.out.println("Database error during /user/login: " + exception.getMessage());
                return "{\"error\": \"Server side error!\"}";
            }

        }
    }
