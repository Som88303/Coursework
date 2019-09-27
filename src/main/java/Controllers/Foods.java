package Controllers;

import Server.Main;
import com.sun.jersey.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@Path("food")
public class Foods {
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public static String ListFoods() {
        System.out.println("Foods/list");
        JSONArray list = new JSONArray();
        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT FoodID, FoodName, PortionSize, HealthyPoint, MealType FROM Foods");
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                JSONObject Food = new JSONObject();
                Food.put("FoodID", results.getInt(1));
                Food.put("FoodName", results.getString(2));
                Food.put("PortionSize", results.getString(3));
                Food.put("HealthyPoint", results.getInt(4));
                Food.put("MealType", results.getString(5));
                list.add(Food);
            }
            return list.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to list items, please see server console for more info.\"}";
        }
    }

}


