package Controllers;

import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("Exercise")
public class Exercises {
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String ListFood() {
        System.out.println("Exercises/list");
        JSONArray list = new JSONArray();
        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT ExerciseID, ExerciseName, CalPerHour FROM Exercises");
            // Selects attributes from the table Controllers.Foods
            ResultSet results = ps.executeQuery();
            // helps retrieve and modify the data inside the database
            while (results.next()) {
                JSONObject Exercise = new JSONObject();
                Exercise.put("ExerciseID", results.getInt(1));
                Exercise.put("ExerciseName", results.getString(2));
                Exercise.put("CalPerHour", results.getInt(3));
                list.add(Exercise);
            }
            return list.toString();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            // If something goes wrong with the code this error message comes up
            return "{\"error\": \"Unable to list items, please see server console for more info.\"}";
        }
    }

    @POST
    @Path("add")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String InsertFood(@FormDataParam("ExerciseName") String ExerciseName, @FormDataParam("CalPerHour") Integer CalPerHour){
        // Takes in parameters given by the user for the corresponding attributes of the table Controllers.Foods
        try {
            if (ExerciseName == null || CalPerHour == null){
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("Exercise/add ");
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Exercises(ExerciseName, CalPerHour) VALUES (?, ?)");
            // Asks for corresponding attributes
            ps.setString(1, ExerciseName);
            ps.setInt(2, CalPerHour);
            ps.executeUpdate();                                       // Finally, adds the Food given by the the user
            return "{\"status\": \"OK\"}";
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
            // If something goes wrong with the code this error message comes up
            return "{\"error\": \"Unable to create new item, please see server console for more info.\"}";
        }
    }

    @POST
    @Path("update")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String UpdateFood(@FormDataParam("ExerciseID") Integer ExerciseID, @FormDataParam("ExerciseName") String ExerciseName, @FormDataParam("CalPerHour") Integer CalPerHour) {
        // Takes in parameters given by the user for the corresponding attributes of the table Controllers.Foods
        try {
            if (ExerciseID == null || ExerciseName == null || CalPerHour == null ) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("Exercise/update=" + ExerciseID);
            PreparedStatement ps = Main.db.prepareStatement(
                    "UPDATE Exercises SET ExerciseID = ?, ExerciseName = ?, CalPerHour = ? WHERE ExerciseID = ?");
            // Updates the attributes in the table Controllers.Foods for the corresponding FoodID given by the user
            ps.setString(1, ExerciseName);
            ps.setInt(2, CalPerHour);
            ps.setInt(3, ExerciseID);
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
    public String DeleteFood(@FormDataParam("ExerciseID") Integer ExerciseID) {
        // Asks for a parameter to the Food the user wants to delete(FoodID)
        try {
            if (ExerciseID == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("Exercise/delete" + ExerciseID);
            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Exercises WHERE ExerciseID = ?");
            // Deletes all attributes that associate with the given parameter by the user

            ps.setInt(1, ExerciseID);       // Gives the statement which FoodID to delete
            ps.execute();                           // Finally, deletes the Food entered by the user
            System.out.print("ExerciseID: " + ExerciseID+ " deleted \n");
            return "{\"status\": \"OK\"}";


        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            // If something goes wrong with the code this error message comes up
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
        }

    }
}