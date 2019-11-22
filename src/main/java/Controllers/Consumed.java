package Controllers;

import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("Consumed")
public class Consumed {
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)

    public String ListConsumed() {
        System.out.println("Consumed/list");
        JSONArray list = new JSONArray();

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT ConsumedID, FoodID, UserID, MealType, PortionSize, DateEaten, HealthyPoints FROM Consumed");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                JSONObject Consumed = new JSONObject();
                Consumed.put("ConsumedID", results.getInt(1));
                Consumed.put("FoodID", results.getInt(2));
                Consumed.put("UserID", results.getInt(3));
                Consumed.put("MealType", results.getString(4));
                Consumed.put("PortionSize", results.getInt(5));
                Consumed.put("DateEaten", results.getString(6));
                Consumed.put("HealthyPoints", results.getInt(7));
                list.add(Consumed);
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


        public String InsertConsumed(@FormDataParam("FoodID") Integer FoodID, @FormDataParam("UserID") Integer UserID,
                @FormDataParam("MealType") String MealType, @FormDataParam("PortionSize") Integer PortionSize, @FormDataParam("DateEaten") String DateEaten,
                @FormDataParam("HealthyPoints") Integer HealthyPoints) {
            System.out.println("Consumed/add");
            try {
                if (FoodID == null || UserID == null ||MealType == null || PortionSize == null || DateEaten == null || HealthyPoints == null || HealthyPoints == null  ) {
                    throw new Exception("One or more form data parameters are missing in the HTTP request.");
                }
                PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Consumed(FoodID, UserID, MealType, PortionSize, DateEaten, HealthyPoints) VALUES (?, ?, ?, ?, ?, ?)");
                ps.setInt(1, FoodID);
                ps.setInt(2, UserID);
                ps.setString(3, MealType);
                ps.setInt(4, PortionSize);
                ps.setString(5, DateEaten);
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


