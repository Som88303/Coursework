package Controllers;

import Server.Main;
import com.sun.jersey.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Server.Main.db;

class ListFoods {
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
    class InsertFood {
        @POST
        @Path("new")
        @Consumes(MediaType.MULTIPART_FORM_DATA)
        @Produces(MediaType.APPLICATION_JSON)
        public String insertFood(
                @FormDataParam("FoodID") Integer FoodID, @FormDataParam("FoodName") String FoodName, @FormDataParam("PortionSize") String PortionSize, @FormDataParam("HealthyPoint") Integer HealthyPoint, @FormDataParam("MealType") String MealType){
            try {
                if (FoodID == null || FoodName == null || PortionSize == null || HealthyPoint == null || MealType == null) {
                    throw new Exception("One or more form data parameters are missing in the HTTP request.");
                }
                System.out.println("Food/new FoodID=" + FoodID);

                PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Foods (FoodID, FoodName, PortionSize, HealthyPoint, MealType) VALUES (?, ?, ?, ?, ?)");
                ps.setInt(1, FoodID);
                ps.setString(2, FoodName);
                ps.setString(3, PortionSize);
                ps.setInt(4, HealthyPoint);
                ps.setString(5, MealType);
                ps.execute();
                return "{\"status\": \"OK\"}";

            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
                return "{\"error\": \"Unable to create new item, please see server console for more info.\"}";
            }

        }
    }
class UpdateFood{
    @POST
    @Path("update")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateFood(
            @FormDataParam("FoodID") Integer FoodID, @FormDataParam("FoodName") String FoodName, @FormDataParam("PortionSize") String PortionSize, @FormDataParam("HealthyPoint") Integer HealthyPoint, @FormDataParam("MealType") String MealType){
        try {
            if (FoodID == null || FoodName == null || PortionSize == null || HealthyPoint == null || MealType == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("Food/new FoodID=" + FoodID);

            PreparedStatement ps = Main.db.prepareStatement("UPDATE Foods SET FoodName = ?, PortionSize = ?, HealthyPoint = ?, MealType = ? WHERE FoodID = ?");
            ps.setString(1, FoodName);
            ps.setString(2, PortionSize);
            ps.setInt(3, HealthyPoint);
            ps.setString(4, MealType);
            ps.setInt(5, FoodID);
            ps.execute();
            return "{\"status\": \"OK\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";
        }
    }
}
class DeleteFood{
    @POST
    @Path("delete")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteFood(@FormDataParam("FoodID") Integer FoodID) {

        try {
            if (FoodID == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("Food/delete FoodID=" + FoodID);

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Foods WHERE FoodID = ?");

            ps.setInt(1, FoodID);

            ps.execute();

            return "{\"status\": \"OK\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
        }
    }

}



