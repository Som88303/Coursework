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
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Sessions(ExerciseID, UserID, DateExercised, Duration) VALUES (?, ?, ?, ?)");
            ps.setInt(1, ExerciseID);
            ps.setInt(2, UserID);
            ps.setString(3, DateExercised);
            ps.setInt(4, Duration);
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
    public String UpdateConsumed( @FormDataParam("SessionID") Integer SessionID, @FormDataParam("ExerciseID") Integer ExerciseID, @FormDataParam("UserID") Integer UserID,
                                  @FormDataParam("DateExercised") String DateExercised, @FormDataParam("Duration") Integer Duration){
        System.out.println("Session/update");

        try {
            if (SessionID == null || ExerciseID == null || UserID == null || DateExercised == null ||Duration == null ) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            PreparedStatement ps = Main.db.prepareStatement(
                    "UPDATE Sessions SET ExerciseID = ?, UserID = ?, DateExercised = ?, Duration = ? WHERE SessionID = ?");

            ps.setInt(1, ExerciseID);
            ps.setInt(2, UserID);
            ps.setString(3, DateExercised);
            ps.setInt(4, Duration);
            ps.setInt(5, SessionID);

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
    public String DeleteConsumed(@FormDataParam("SessionID") Integer SessionID) {
        System.out.println("Session/delete");

        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Sessions WHERE SessionID = ?");

            ps.setInt(1, SessionID);
            ps.execute();
            System.out.print("SessionID: " + SessionID+ " deleted \n");
            return "{\"status\": \"OK\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
        }
    }
}


