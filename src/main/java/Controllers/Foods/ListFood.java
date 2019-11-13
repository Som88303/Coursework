package Controllers.Foods;


import Server.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("Foods")
public class ListFood {
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String ListFood() {
        System.out.println("Foods/list");
        JSONArray list = new JSONArray();
        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT FoodID, FoodName, Proteins, Carbohydrates, Fats, CalPerHundredGrams, HealthyPoints, PortionSize FROM Foods");
            // Selects attributes from the table Foods
            ResultSet results = ps.executeQuery();
            // helps retrieve and modify the data inside the database
            while (results.next()) {
                JSONObject Food = new JSONObject();
                Food.put("FoodID", results.getInt(1));
                Food.put("FoodName", results.getInt(2));
                Food.put("Proteins", results.getInt(3));
                Food.put("Carbohydrates", results.getInt(4));
                Food.put("Fats", results.getInt(5));
                Food.put("CalPerHundredGrams", results.getInt(6));
                Food.put("HealthyPoints", results.getInt(7));
                Food.put("PortionSize", results.getInt(8));
                list.add(Food);
            }
            return list.toString();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            // If something goes wrong with the code this error message comes up
            return "{\"error\": \"Unable to list items, please see server console for more info.\"}";
        }


    }

}
