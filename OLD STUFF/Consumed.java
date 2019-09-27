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

class ListConsumed {
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public static String ListConsumed() {
        System.out.println("Consumed/list");
        JSONArray list = new JSONArray();
        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT EntryID, FoodID, UserID, DateEaten FROM Foods");
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                JSONObject Consumed = new JSONObject();
                Consumed.put("EntryID", results.getInt(1));
                Consumed.put("FoodID", results.getInt(2));
                Consumed.put("UserID", results.getInt(3));
                Consumed.put("DateEaten", results.getString(4));
                list.add(Consumed);
            }
            return list.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to list items, please see server console for more info.\"}";
        }
    }
}
class DeleteConsumed{
    @POST
    @Path("delete")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String DeleteConsumed(@FormDataParam("FoodID") Integer EntryID) {

        try {
            if (EntryID == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("Consumed/delete EntryID=" + EntryID);

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Consumed WHERE EntryID = ?");

            ps.setInt(1, EntryID);

            ps.execute();

            return "{\"status\": \"OK\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
        }
    }

}

 */