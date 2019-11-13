
package Controllers.Foods;

import Server.Main;

import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;


@Path("Controllers/Foods")
public class InsertFood {
    @POST
    @Path("add")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String InsertFood(@FormDataParam("FoodID") Integer FoodID, @FormDataParam("FoodName") String FoodName, @FormDataParam("Proteins") Integer Proteins,
                             @FormDataParam("Carbohydrates") Integer Carbohydrates, @FormDataParam("Fats") Integer Fats, @FormDataParam("CalPerHundredGrams") Integer CalPerHundredGrams,
                             @FormDataParam("HealthyPoints") Integer HealthyPoints, @FormDataParam("PortionSize") Integer PortionSize) {
        // Takes in parameters given by the user for the corresponding attributes of the table Controllers.Foods
        try {
            if (FoodID == null || FoodName == null || Proteins == null ||Carbohydrates == null || Fats == null || CalPerHundredGrams == null || HealthyPoints == null || PortionSize == null  ) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("Controllers/Foods/add =" + FoodID);
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Foods(FoodID, Foodname, Proteins, Carbohydrates, Fats, CalPerHundredGrams, HealthyPoints, PortionSize) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            // Asks for corresponding attributes
            ps.setInt(1, FoodID);
            ps.setString(2, FoodName);
            ps.setInt(3, Proteins);
            ps.setInt(4, Carbohydrates);                // Gives back the attributes asked for by the statement
            ps.setInt(5, Fats);
            ps.setInt(6, CalPerHundredGrams);
            ps.setInt(7, HealthyPoints);
            ps.setInt(8, PortionSize);
            ps.execute();                                       // Finally, adds the Food given by the the user
            return "{\"status\": \"OK\"}";
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
            // If something goes wrong with the code this error message comes up
            return "{\"error\": \"Unable to create new item, please see server console for more info.\"}";
        }
    }
}
