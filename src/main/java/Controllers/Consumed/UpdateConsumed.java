package Controllers.Consumed;

import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;


@Path("Controllers/Consumed")
public class UpdateConsumed {
    @POST
    @Path("update")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String UpdateConsumed(@FormDataParam("ConsumedID") Integer ConsumedID, @FormDataParam("FoodID") Integer FoodID, @FormDataParam("UserID") Integer UserID,
                                 @FormDataParam("MealType") String MealType, @FormDataParam("PortionSize") Integer PortionSize, @FormDataParam("DateEaten") String DateEaten,
                                 @FormDataParam("HealthyPoints") Integer HealthyPoints) {

        try {

            PreparedStatement ps = Main.db.prepareStatement(
                    "UPDATE Consumed SET FoodID = ?, UserID = ?, MealType = ?, PortionSize = ?, DateEaten = ?, HealthyPoints = ? WHERE ConsumedID = ?");

            ps.setInt(1, FoodID);
            ps.setInt(2, UserID);
            ps.setString(3, MealType);
            ps.setInt(4, PortionSize);
            ps.setString(5, DateEaten);
            ps.setInt(6, HealthyPoints);
            ps.setInt(7, ConsumedID);
            ps.executeUpdate();
            return "{\"status\": \"OK\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";
        }
    }

}
