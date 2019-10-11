package Controllers.Foods;

import Server.Main;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
@Path("foods")
public class FoodDelete {
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
