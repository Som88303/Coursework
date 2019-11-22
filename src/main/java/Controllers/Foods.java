package Controllers;

import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("Foods")
public class Foods {
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String ListFood() {
        System.out.println("Foods/list");
        JSONArray list = new JSONArray();
        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT FoodID, FoodName, Proteins, Carbohydrates, Fats, CalPerHundredGrams, HealthyPoints, PortionSize FROM Foods");
            // Selects attributes from the table Controllers.Foods
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

    @POST
    @Path("add")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String InsertFood(@FormDataParam("FoodName") String FoodName, @FormDataParam("Proteins") Integer Proteins,
                             @FormDataParam("Carbohydrates") Integer Carbohydrates, @FormDataParam("Fats") Integer Fats, @FormDataParam("CalPerHundredGrams") Integer CalPerHundredGrams,
                             @FormDataParam("HealthyPoints") Integer HealthyPoints, @FormDataParam("PortionSize") Integer PortionSize) {
        // Takes in parameters given by the user for the corresponding attributes of the table Controllers.Foods
        try {
            if (FoodName == null || Proteins == null ||Carbohydrates == null || Fats == null || CalPerHundredGrams == null || HealthyPoints == null || PortionSize == null  ) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("Controllers/Foods/add =");
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Foods(Foodname, Proteins, Carbohydrates, Fats, CalPerHundredGrams, HealthyPoints, PortionSize) VALUES (?, ?, ?, ?, ?, ?, ?)");
            // Asks for corresponding attributes
            ps.setString(1, FoodName);
            ps.setInt(2, Proteins);
            ps.setInt(3, Carbohydrates);                // Gives back the attributes asked for by the statement
            ps.setInt(4, Fats);
            ps.setInt(5, CalPerHundredGrams);
            ps.setInt(6, HealthyPoints);
            ps.setInt(7, PortionSize);
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
    public String UpdateFood(@FormDataParam("FoodID") Integer FoodID, @FormDataParam("FoodName") String FoodName, @FormDataParam("Proteins") Integer Proteins,
                             @FormDataParam("Carbohydrates") Integer Carbohydrates, @FormDataParam("Fats") Integer Fats, @FormDataParam("CalPerHundredGrams") Integer CalPerHundredGrams,
                             @FormDataParam("HealthyPoints") Integer HealthyPoints, @FormDataParam("PortionSize") Integer PortionSize) {
        // Takes in parameters given by the user for the corresponding attributes of the table Controllers.Foods
        try {
            if (FoodID == null || FoodName == null || Proteins == null ||Carbohydrates == null || Fats == null || CalPerHundredGrams == null || HealthyPoints == null || PortionSize == null  ) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("Foods/update=" + FoodID);
            PreparedStatement ps = Main.db.prepareStatement(
                    "UPDATE Foods SET FoodName = ?, Proteins = ?, Carbohydrates = ?, Fats = ?, CalPerHundredGrams = ? , HealthyPoints = ?, PortionSize = ?  WHERE FoodID = ?");
            // Updates the attributes in the table Controllers.Foods for the corresponding FoodID given by the user
            ps.setString(1, FoodName);
            ps.setInt(2, Proteins);
            ps.setInt(3, Carbohydrates);
            ps.setInt(4, Fats);                 // Sets the parameters to the corresponding index in the table
            ps.setInt(5, CalPerHundredGrams);
            ps.setInt(6, HealthyPoints);
            ps.setInt(7, PortionSize);
            ps.setInt(8, FoodID);
            ps.executeUpdate();                               // Finally, sets the given parameters in the database itself
            return "{\"status\": \"OK\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            // If something goes wrong with the code this error message comes up
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";
        }
    }

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

        @POST
        @Path("delete")
        @Consumes(MediaType.MULTIPART_FORM_DATA)
        @Produces(MediaType.APPLICATION_JSON)
        public String DeleteFood(@FormDataParam("FoodID") Integer FoodID) {
            // Asks for a parameter to the Food the user wants to delete(FoodID)
            try {
                if (FoodID == null) {
                    throw new Exception("One or more form data parameters are missing in the HTTP request.");
                }
                System.out.println("Controllers/Foods/delete" + FoodID);
                PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Foods WHERE FoodID = ?");
                // Deletes all attributes that associate with the given parameter by the user

                ps.setInt(1, FoodID);       // Gives the statement which FoodID to delete
                ps.execute();                           // Finally, deletes the Food entered by the user
                System.out.print("FoodID: " + FoodID+ " deleted \n");
                return "{\"status\": \"OK\"}";


            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
                // If something goes wrong with the code this error message comes up
                return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
            }

        }
    }
