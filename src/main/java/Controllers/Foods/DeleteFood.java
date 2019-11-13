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
public class DeleteFood {
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

