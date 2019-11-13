
package Controllers.Foods;

import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
@Path("Foods")
public class UpdateFood {
    @POST
    @Path("update")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String UpdateFood(@FormDataParam("FoodID") Integer FoodID, @FormDataParam("FoodName") String FoodName, @FormDataParam("Proteins") Integer Proteins,
                             @FormDataParam("Carbohydrates") Integer Carbohydrates, @FormDataParam("Fats") Integer Fats, @FormDataParam("CalPerHundredGrams") Integer CalPerHundredGrams,
                             @FormDataParam("HealthyPoints") Integer HealthyPoints, @FormDataParam("PortionSize") Integer PortionSize) {
        // Takes in parameters given by the user for the corresponding attributes of the table Foods
        try {
            if (FoodID == null || FoodName == null || Proteins == null ||Carbohydrates == null || Fats == null || CalPerHundredGrams == null || HealthyPoints == null || PortionSize == null  ) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("Foods/update=" + FoodID);
            PreparedStatement ps = Main.db.prepareStatement(
                    "UPDATE Foods SET FoodName = ?, Proteins = ?, Carbohydrates = ?, Fats = ?, CalPerHundredGrams = ? , HealthyPoints = ?, PortionSize = ?  WHERE FoodID = ?");
            // Updates the attributes in the table Foods for the corresponding FoodID given by the user
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
}


