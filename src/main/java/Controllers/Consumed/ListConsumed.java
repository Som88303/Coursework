package Controllers.Consumed;

import Server.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@Path("Controllers/Consumed")
public class ListConsumed {
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)

    public String ListConsumed() {
        System.out.println("Controllers/Foods/list");
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
}
