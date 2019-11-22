package Controllers;

import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("Sessions")
public class Sessions {
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)

    public String ListConsumed() {
        System.out.println("Sessions/list");
        JSONArray list = new JSONArray();

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT SessionID, ExerciseID, UserID, DateExercised, Duration FROM Sessions");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                JSONObject Session = new JSONObject();
                Session.put("SessionID", results.getInt(1));
                Session.put("ExerciseID", results.getInt(2));
                Session.put("UserID", results.getInt(3));
                Session.put("DateExercised", results.getString(4));
                Session.put("Duration", results.getInt(5));
                list.add(Session);
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


    public String InsertConsumed( @FormDataParam("ExerciseID") Integer ExerciseID,
                                 @FormDataParam("UserID") Integer UserID, @FormDataParam("DateExercised") String DateExercised, @FormDataParam("Duration") Integer Duration){
        System.out.println("Sessions/add");
        try {
            if ( ExerciseID == null ||UserID == null || DateExercised == null || Duration == null ) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Sessions(ExerciseID, UserID, DateExercised, Duration) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(2, ExerciseID);
            ps.setString(3, UserID);
            ps.setInt(4, DateExercised);
            ps.setString(5, Duration);
            ps.setInt(6, HealthyPoints);
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
    public String UpdateConsumed(@FormDataParam("ConsumedID") Integer ConsumedID, @FormDataParam("FoodID") Integer FoodID, @FormDataParam("UserID") Integer UserID,
                                 @FormDataParam("MealType") String MealType, @FormDataParam("PortionSize") Integer PortionSize, @FormDataParam("DateEaten") String DateEaten,
                                 @FormDataParam("HealthyPoints") Integer HealthyPoints) {
        System.out.println("Consumed/update");

        try {

            PreparedStatement ps = Main.db.prepareStatement(
                    "UPDATE Consumed SET FoodID = ?, UserID = ?, MealType = ?, PortionSize = ?, DateEaten = ?, HealthyPoints = ? WHERE ConsumedID = ?");

            ps.setInt(1, FoodID);
            ps.setInt(2, UserID);
            ps.setString(3, MealType);
            ps.setInt(4, PortionSize);
            ps.setString(5, DateEaten);
            ps.setInt(6, HealthyPoints);
            ps.setInt(7, ConsumedID);
            ps.executeUpdate();
            return "{\"status\": \"OK\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";
        }
    }

    @POST
    @Path("delete")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String DeleteConsumed(@FormDataParam("ConsumedID") Integer ConsumedID) {
        System.out.println("Consumed/delete");

        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Consumed WHERE ConsumedID = ?");

            ps.setInt(1, ConsumedID);
            ps.execute();
            System.out.print("ConsumedID: " + ConsumedID+ " deleted \n");
            return "{\"status\": \"OK\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
        }
    }
}


