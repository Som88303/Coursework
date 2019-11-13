package Controllers.Foods;

import Server.Main;
import org.json.simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("Controllers/Foods")
public class GetFood {
    @GET
    @Path("get/{FoodID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String GetFood(@PathParam("FoodID") Integer FoodID) throws Exception {
        if (FoodID == null) {
            throw new Exception("Thing's 'id' is missing in the HTTP request's URL.");
        }
        System.out.println("thing/get/" + FoodID);
        JSONObject food = new JSONObject();
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT FoodName, Proteins, Carbohydrates, Fats, CalPerHundredGrams, HealthyPoints, PortionSize FROM Foods WHERE FoodID = ?");
            ps.setInt(1, FoodID);
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                food.put("FoodName", results.getString(1));
                food.put("Proteins", results.getInt(2));
                food.put("Carbohydrates", results.getInt(3));
                food.put("Fats", results.getInt(4));
                food.put("CalPerHundredGrams", results.getInt(5));
                food.put("HealthyPoints", results.getInt(6));
                food.put("PortionSize", results.getInt(7));

            }
            return food.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to get item, please see server console for more info.\"}";
        }
    }

}
